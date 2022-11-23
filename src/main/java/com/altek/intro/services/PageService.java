package com.altek.intro.services;

import com.altek.intro.dto.request.BaseRequest;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.response.BaseResponse;


@Service
public interface PageService extends AbstractService {

    BaseResponse getAll();

    BaseResponse listPageContentByMenuId(Long id);

    BaseResponse create(PageRequestDto request);

    BaseResponse delete(Long id);

//    BaseResponse listPageContent(BaseRequest requestDto);
}
