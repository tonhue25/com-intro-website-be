package com.altek.intro.services.impl;

import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.exceptions.MissingServletRequestParameterException;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.services.MenuService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ModelMapper modelMapper;

    private static final String DES_SUC = "su.des.suc.";
    private static final String DES_FAIL = "ex.des.fai.";

    @Autowired
    private ResponseUtil responseUtil;

    @Override
    public List<MenuDTO> getAll() {
        try {
            List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
            List<MenuEntity> menuEntities = menuRepository.findAll();
            MenuDTO dto = new MenuDTO();
            if (CollectionUtils.isNotEmpty(menuEntities)) {
                menuDTOs = menuEntities.stream().map(item -> (MenuDTO) menuMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return menuDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(MenuRequestDto request) {
        try {
            MenuEntity entity = new MenuEntity();
            entity = (MenuEntity) menuMapper.convertToEntity(request, entity);
            entity = menuRepository.save(entity);
            MenuDTO response = modelMapper.map(entity, MenuDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, "add.or.update.menu", response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM, "ex.common.system.error.");
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try {
            Optional<MenuEntity> optional = menuRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
            }
            MenuEntity entity = optional.get();
            entity.setStatus(Constant.DELETE);
            entity = menuRepository.save(entity);
            MenuDTO response = modelMapper.map(entity, MenuDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, String.format("delete.menu.with.id:%s", id), response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
        }
    }
}
