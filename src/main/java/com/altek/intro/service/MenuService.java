package com.altek.intro.service;


import org.springframework.stereotype.Service;
import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface MenuService extends AbstractService {
    BaseResponse getMenus(String lang);

    BaseResponse getNavNews(String language, Long parentId);
}
