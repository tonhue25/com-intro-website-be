package com.altek.intro.services.impl;

import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.SliderDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.SliderEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.services.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuDTO> getAllMenu() {
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
}
