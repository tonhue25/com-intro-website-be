package com.altek.intro.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entities.MenuTranslate;
import com.altek.intro.repository.MenuTranslateRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.MenuResponseDto;

import com.altek.intro.entities.Menu;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.services.MenuService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;

@Service
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
            return new BaseResponse(Constant.SUCCESS, "get.list.nav.news", response);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
        }
    }

}
