package com.altek.intro.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.MenuTranslate;
import com.altek.intro.entity.Menu;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.repository.MenuTranslateRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.MenuResponseDto;

import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.service.MenuService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;

import javax.transaction.Transactional;

@Service
@Slf4j
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MenuTranslateRepository menuTranslateRepository;

    @Override
    public BaseResponse getAll(String lang) {
        try {
            List<MenuResponseDto> menuDTOs = new ArrayList<>();
            List<MenuTranslate> menus = menuTranslateRepository.get(lang);
            if (CollectionUtils.isNotEmpty(menus)) {
                menuDTOs = menus.stream().map(item -> modelMapper.map(item, MenuResponseDto.class))
                        .collect(Collectors.toList());
            }
            ListResponseDto<MenuResponseDto> response = new ListResponseDto<>();
            response.setList(menuDTOs);
            response.setPage(1);
            response.setSize(menuDTOs.size());
            response.setTotalPages(1);
            response.setRecordPerPage(menuDTOs.size());
            response.setLanguage(lang);
            return new BaseResponse(Constant.SUCCESS, "get.list.menu", response);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
        }
    }

    @Override
    public BaseResponse create(MenuRequestDto request) {
        Menu entity = new Menu();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<Menu> optional = menuRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (Menu) menuMapper.convertToEntity(request, entity);
        entity = menuRepository.save(entity);
        MenuResponseDto response = modelMapper.map(entity, MenuResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.menu", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<Menu> optional = menuRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
        }
        Menu entity = optional.get();
        if(entity.getStatus().equals(Constant.DELETE)){
            return new BaseResponse(Constant.SUCCESS, String.format("menu.already.delete.with.id:%s", id),
                    String.format("status.of.menu:%s", entity.getStatus()));
        }
        entity.setStatus(Constant.DELETE);
        entity = menuRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.menu.with.id:%s", id),
                String.format("status.of.menu:%s", entity.getStatus()));
    }

    @Override
    public BaseResponse getNav(String language, Long parentId) {
        try {
            List<MenuResponseDto> menuDTOs = new ArrayList<>();
            List<MenuTranslate> menus = menuTranslateRepository.getNav(language,parentId);
            if (CollectionUtils.isNotEmpty(menus)) {
                menuDTOs = menus.stream().map(item -> modelMapper.map(item, MenuResponseDto.class))
                        .collect(Collectors.toList());
            }
            ListResponseDto<MenuResponseDto> response = new ListResponseDto<>();
            response.setList(menuDTOs);
            response.setPage(1);
            response.setSize(menuDTOs.size());
            response.setTotalPages(1);
            response.setRecordPerPage(menuDTOs.size());
            response.setLanguage(language);
            return new BaseResponse(Constant.SUCCESS, "get.list.nav.menu", response);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse createMenu (MenuRequestDto request) {
        log.info("#MenuServiceImpl.createMenu   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            Menu dtoCheck = menuRepository.findMenuById(request.getId());
            if(Objects.nonNull(dtoCheck) && !dtoCheck.getId().equals(request.getId())){
                reponse.setMessage("common.error.code.already");
                reponse.setHttp_code(HttpStatus.BAD_REQUEST.toString());
                return reponse;
            }

            Menu menuEntiy = new Menu();
            menuEntiy = insertRequestToMenu(menuEntiy ,request);
            menuEntiy = menuRepository.save(menuEntiy);

            MenuTranslate menuTranslateEntity = new MenuTranslate();
            menuTranslateEntity = insertRequestToMenuTranslate(menuTranslateEntity, request, menuEntiy);
            menuTranslateEntity = menuTranslateRepository.save(menuTranslateEntity);

            MenuResponseDto data = new MenuResponseDto();
            data = (MenuResponseDto) menuMapper.convertToDTO(data, menuEntiy);
            data = (MenuResponseDto) menuMapper.convertToDTO(data, menuTranslateEntity);
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
            reponse.setHttp_code(HttpStatus.CREATED.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#MenuServiceImpl.createMenu " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse updateMenu(MenuRequestDto request) {
        log.info("#MenuServiceImpl.updateMenu   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            Menu dtoCheck = menuRepository.findMenuById(request.getId());
            if(!Objects.nonNull(dtoCheck)){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            Menu menuEntiy = new Menu();
            menuEntiy = insertRequestToMenu(menuEntiy ,request);
            menuEntiy = menuRepository.save(menuEntiy);

            MenuTranslate menuTranslateEntity = menuTranslateRepository
                    .findByMenuIdAndLanguageId(request.getId(), request.getLanguageId());
            if(menuTranslateEntity == null){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }
            menuTranslateEntity = insertRequestToMenuTranslate(menuTranslateEntity, request,menuEntiy);
            menuTranslateEntity = menuTranslateRepository.save(menuTranslateEntity);

            MenuResponseDto data = new MenuResponseDto();
            data = (MenuResponseDto) menuMapper.convertToDTO(data, menuEntiy);
            data = (MenuResponseDto) menuMapper.convertToDTO(data, menuTranslateEntity);
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
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

            Menu menuEntiy = menuRepository.findMenuById(request.getId());
            if(!Objects.nonNull(menuEntiy)){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            menuEntiy.setStatus(Constant.DELETE);
            menuEntiy = menuRepository.save(menuEntiy);

            List<MenuTranslate> findBymenuId = menuTranslateRepository.
                    findMenuTranslateByMenuId(menuEntiy.getId());
            for(MenuTranslate item: findBymenuId){
                item.setStatus(Constant.DELETE);
                menuTranslateRepository.save(item);
            }

            MenuResponseDto data = new MenuResponseDto();
            data = (MenuResponseDto) menuMapper.convertToDTO(data, menuEntiy);
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#MenuServiceImpl.deleteMenu " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    private Menu insertRequestToMenu(Menu menuEntiy, MenuRequestDto request){
        menuEntiy = (Menu) menuMapper.convertToEntity(request, menuEntiy);
        menuEntiy.setStatus(Constant.INSERT);
        return menuEntiy;
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
