package com.altek.intro.services;


import org.springframework.stereotype.Service;
import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface MenuService extends AbstractService {
    BaseResponse getAll(String lang);

    BaseResponse create(MenuRequestDto request);

    BaseResponse delete(Long id);

}
