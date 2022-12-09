package com.altek.intro.service;


import org.springframework.stereotype.Service;
import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface MenuService extends AbstractService {
    BaseResponse getAll(String lang);

    BaseResponse create(MenuRequestDto request);

    BaseResponse delete(Long id);

    BaseResponse getNav(String language, Long parentId);
    BaseResponse createMenu(MenuRequestDto request);
    BaseResponse updateMenu(MenuRequestDto request);
    BaseResponse deleteMenu(MenuRequestDto request);
}
