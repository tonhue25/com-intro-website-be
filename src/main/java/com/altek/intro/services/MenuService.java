package com.altek.intro.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface MenuService extends AbstractService{
    List<MenuDTO> getAll();

    BaseResponse create(MenuRequestDto request);

    BaseResponse delete(Long id);
}
