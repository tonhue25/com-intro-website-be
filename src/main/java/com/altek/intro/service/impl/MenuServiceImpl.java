package com.altek.intro.service.impl;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.MenuResponseDto;
import com.altek.intro.entity.Menu;
import com.altek.intro.entity.MenuTranslate;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.MenuTranslateRepository;
import com.altek.intro.service.MenuService;
import com.altek.intro.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MenuTranslateRepository menuTranslateRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResponse getMenus(String lang) {
        try {
            List<MenuTranslate> menuTranslates = menuTranslateRepository.getListMenu(lang);
            List<MenuResponseDto> menuResponseDtos = menuTranslates.stream().map(item -> modelMapper.map(item, MenuResponseDto.class)).collect(Collectors.toList());
            ListResponseDto<MenuResponseDto> response = new ListResponseDto<>(menuResponseDtos, lang);
            return new BaseResponse(Constant.SUCCESS, "get.list.menu", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.menu", e.getMessage());
        }
    }

    @Override
    public BaseResponse getNavNews(String language, Long parentId) {
        try {
            List<MenuTranslate> menuTranslates = menuTranslateRepository.getNav(language, parentId);
            if (!CollectionUtils.isNotEmpty(menuTranslates)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.nav.news", Constant.EMPTY_LIST);
            }
            List<MenuResponseDto> menuResponseDtos = menuTranslates.stream().map(item -> modelMapper.map(item, MenuResponseDto.class)).collect(Collectors.toList());
            ListResponseDto<MenuResponseDto> response = new ListResponseDto<>(menuResponseDtos, language);
            return new BaseResponse(Constant.SUCCESS, "get.list.nav.news", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.nav.news", e.getMessage());
        }
    }


    private Long addNewTranslationForMenuAndReturnId(MenuRequestDto request, Menu menuEntity) {
        MenuTranslate menuTranslateEntity = new MenuTranslate();
        menuTranslateEntity = insertRequestToMenuTranslate(menuTranslateEntity, request, menuEntity);
        menuTranslateEntity = menuTranslateRepository.save(menuTranslateEntity);
        return menuTranslateEntity.getMenu().getId();
    }

    private Long newMenuAndReturnId(MenuRequestDto request) {
        Menu menuEntity = new Menu();
        menuEntity = insertRequestToMenu(menuEntity, request);
        menuEntity = menuRepository.save(menuEntity);

        addNewTranslationForMenuAndReturnId(request, menuEntity);
        return menuEntity.getId();
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse createMenu(MenuRequestDto request) {
        log.info("#MenuServiceImpl.createMenu   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            MenuResponseDto dtoCheckExist = menuTranslateRepository
                    .findByMenuIdAndLanguageId(request.getId(), request.getLanguageId());
            if (Objects.nonNull(dtoCheckExist)) {
                reponse.setMessage("common.error.code.already");
                reponse.setHttp_code(HttpStatus.BAD_REQUEST.toString());
                return reponse;
            }

            long idResponse;
            Menu dtoCheckAddTranslationOrNew = menuRepository.findMenuById(request.getId());
            if (Objects.nonNull(dtoCheckAddTranslationOrNew)) {
                idResponse = addNewTranslationForMenuAndReturnId(request, dtoCheckAddTranslationOrNew);
            } else {
                idResponse = newMenuAndReturnId(request);
            }

            MenuResponseDto data = menuTranslateRepository
                    .findByMenuIdAndLanguageId(idResponse, request.getLanguageId());
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
            reponse.setHttp_code(HttpStatus.CREATED.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#MenuServiceImpl.createNewMenu " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse updateMenu(MenuRequestDto request) {
        log.info("#MenuServiceImpl.updateMenu   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            MenuResponseDto dtoCheckExist = menuTranslateRepository
                    .findByMenuIdAndLanguageId(request.getId(), request.getLanguageId());
            Menu menuEntity = new Menu();
            if (Objects.isNull(dtoCheckExist)) {
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            } else {
                menuEntity = insertRequestToMenu(menuEntity, request);
                menuEntity = menuRepository.save(menuEntity);

                MenuTranslate menuTranslateEntity = menuTranslateRepository.findMenuTranslateByMenuIdAndLanguageId(request.getId(), request.getLanguageId());
                menuTranslateEntity = insertRequestToMenuTranslate(menuTranslateEntity, request, menuEntity);
                menuTranslateRepository.save(menuTranslateEntity);
            }

            MenuResponseDto data = menuTranslateRepository
                    .findByMenuIdAndLanguageId(menuEntity.getId(), request.getLanguageId());
            reponse.setData(data);
            reponse.setMessage("common.update.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#MenuServiceImpl.updateMenu " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse deleteMenu(MenuRequestDto request) {
        log.info("#MenuServiceImpl.deleteMenu   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            Menu menuEntity = menuRepository.findMenuById(request.getId());
            if (!Objects.nonNull(menuEntity)) {
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            menuEntity.setStatus(Constant.DELETE);
            menuEntity = menuRepository.save(menuEntity);

            List<MenuTranslate> findByMenuId = menuTranslateRepository.
                    findMenuTranslateByMenuId(menuEntity.getId());
            for (MenuTranslate item : findByMenuId) {
                item.setStatus(Constant.DELETE);
                menuTranslateRepository.save(item);
            }

            MenuResponseDto data = new MenuResponseDto();
            data = (MenuResponseDto) menuMapper.convertToDTO(data, menuEntity);
            reponse.setData(data);
            reponse.setMessage("common.delete.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#MenuServiceImpl.deleteMenu " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    private Menu insertRequestToMenu(Menu menuEntity, MenuRequestDto request) {
        menuEntity = (Menu) menuMapper.convertToEntity(request, menuEntity);
        menuEntity.setStatus(Constant.INSERT);
        return menuEntity;
    }

    private MenuTranslate insertRequestToMenuTranslate(MenuTranslate menuTranslateEntity,
                                                       MenuRequestDto request,
                                                       Menu menu) {
        menuTranslateEntity.setLabel(request.getLabel());
        menuTranslateEntity.setLink(request.getLink());
        menuTranslateEntity.setLanguageId(request.getLanguageId());
        menuTranslateEntity.setStatus(Constant.INSERT);
        menuTranslateEntity.setMenu(menu);
        return menuTranslateEntity;
    }
}

