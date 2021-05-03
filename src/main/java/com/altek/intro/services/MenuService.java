package com.altek.intro.services;


import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.entites.MenuEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService extends AbstractService{
    List<MenuViewDTO> getAllMenu() throws Exception;
    MenuViewDTO getMenuById(Long id) throws Exception;
}
