package com.altek.intro.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.altek.intro.dto.response.CandidateResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.MenuTranslate;
import com.altek.intro.repository.MenuTranslateRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.MenuResponseDto;

import com.altek.intro.entity.Menu;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.service.MenuService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;

@Service
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MenuTranslateRepository menuTranslateRepository;

    @Override
    public BaseResponse getListMenu(String lang) {
        try {
            List<MenuTranslate> listEntity = menuTranslateRepository.getListMenu(lang);
            if (!CollectionUtils.isNotEmpty(listEntity)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.menu", Constant.EMPTY_LIST);
            }
            List<MenuResponseDto> listDto = listEntity.stream().map(item -> modelMapper.map(item, MenuResponseDto.class)).collect(Collectors.toList());
            ListResponseDto<MenuResponseDto> response = new ListResponseDto<>(listDto,lang);
            return new BaseResponse(Constant.SUCCESS, "get.list.menu", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.menu", e.getMessage());
        }
    }

    @Override
    public BaseResponse getNav(String language, Long parentId) {
        try {
            List<MenuTranslate> listEntity = menuTranslateRepository.getNav(language, parentId);
            if (!CollectionUtils.isNotEmpty(listEntity)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.nav.news", Constant.EMPTY_LIST);
            }
            List<MenuResponseDto> listDto = listEntity.stream().map(item -> modelMapper.map(item, MenuResponseDto.class)).collect(Collectors.toList());
            ListResponseDto<MenuResponseDto> response = new ListResponseDto<>(listDto,language);
            return new BaseResponse(Constant.SUCCESS, "get.list.nav.news", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.nav.news", e.getMessage());
        }
    }
}
