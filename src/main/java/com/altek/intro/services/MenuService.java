package com.altek.intro.services;


import com.altek.intro.dto.request.MenuRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService extends AbstractService{
    List<MenuRequestDTO> getAll();
}
