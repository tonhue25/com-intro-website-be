package com.altek.intro.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public BaseResponse getAll() {
        try {
            List<MenuResponseDto> menuDTOs = new ArrayList<MenuResponseDto>();
            List<Menu> menuEntities = menuRepository.findAll();
            MenuResponseDto dto = new MenuResponseDto();
            if (CollectionUtils.isNotEmpty(menuEntities)) {
                menuDTOs = menuEntities.stream().map(item -> (MenuResponseDto) menuMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return new BaseResponse(Constant.SUCCESS, "get.list.menu", menuDTOs);
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

}
