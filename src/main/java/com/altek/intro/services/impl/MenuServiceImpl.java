package com.altek.intro.services.impl;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.MenuResponseDTO;
import com.altek.intro.entites.MenuEntity;
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

    @Autowired
    private ResponseUtil responseUtil;

    @Override
    public List<MenuResponseDTO> getAll() {
        try {
            List<MenuResponseDTO> menuDTOs = new ArrayList<MenuResponseDTO>();
            List<MenuEntity> menuEntities = menuRepository.findAll();
            MenuResponseDTO dto = new MenuResponseDTO();
            if (CollectionUtils.isNotEmpty(menuEntities)) {
                menuDTOs = menuEntities.stream().map(item -> (MenuResponseDTO) menuMapper.convertToDTO(dto, item))
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
            MenuResponseDTO response = modelMapper.map(entity, MenuResponseDTO.class);
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
            MenuResponseDTO response = modelMapper.map(entity, MenuResponseDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, String.format("delete.menu.with.id:%s", id), response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
        }
    }

}
