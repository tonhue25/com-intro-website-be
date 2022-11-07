package com.altek.intro.services;


import com.altek.intro.dto.MenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService extends AbstractService{
    List<MenuDTO> getAll();
}
