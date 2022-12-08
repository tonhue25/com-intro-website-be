package com.altek.intro.service;

import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.request.PageTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;


@Service
public interface PageService extends AbstractService {

    BaseResponse getAllPageContent(String lang);

    BaseResponse getAllPageContentByMenuId(PageRequestDto requestBody);

    BaseResponse getPage(Long id, String lang);

    BaseResponse createUpdatePage(PageTranslateRequestDto request);

    BaseResponse deletePage(Long id);
}
