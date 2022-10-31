package com.altek.intro.services.impl;

import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.entites.MenuEntity;
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
    public List<MenuViewDTO> getAllMenu() throws Exception {
        try {
            List<MenuViewDTO> MenuDTOs = new ArrayList<MenuViewDTO>();

            List<MenuEntity> MenuEntities = menuRepository.findAll();
            MenuViewDTO dto = new MenuViewDTO();
            if (CollectionUtils.isNotEmpty(MenuEntities)) {
                MenuDTOs = MenuEntities.stream().map(item -> (MenuViewDTO) menuMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return MenuDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public MenuViewDTO getMenuById(Long id) throws Exception {
        try {
            MenuViewDTO menuViewDTO = new MenuViewDTO();
            MenuEntity menuEntity = menuRepository.findById(id).get();
            menuViewDTO = (MenuViewDTO) menuMapper.convertToDTO(menuViewDTO,menuEntity);
            return menuViewDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
