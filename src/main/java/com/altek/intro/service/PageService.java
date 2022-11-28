package com.altek.intro.service;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.response.BaseResponse;


@Service
public interface PageService extends AbstractService {

    BaseResponse getAllPageContent(String lang);

    BaseResponse getAllPageContentByMenuId(PageRequestDto requestBody);

    BaseResponse create(PageRequestDto request);

    BaseResponse delete(Long id);
}
