package com.altek.intro.service;


import org.springframework.stereotype.Service;
import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface MenuService extends AbstractService {
    BaseResponse getListMenu(String lang);

    BaseResponse getNav(String language, Long parentId);
}
