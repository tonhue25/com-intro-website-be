package com.altek.intro.service;


import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface MenuService extends AbstractService {
    BaseResponse getMenus(String lang);

    BaseResponse getNavNews(String language, Long parentId);

    BaseResponse createMenu(MenuRequestDto request);

    BaseResponse updateMenu(MenuRequestDto request);

    BaseResponse deleteMenu(MenuRequestDto request);
}
