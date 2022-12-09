package com.altek.intro.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDto;

public interface NewsService extends AbstractService{
    BaseResponse findNewsById(String language, Long newsId);
    NewsResponseDto create(NewsRequestDto request);
    NewsResponseDto delete(Long id);
    BaseResponse getList(BaseRequest requestDto);
    BaseResponse createNews(NewsRequestDto request);
    BaseResponse updateNews(NewsRequestDto request);
    BaseResponse deleteNews(NewsRequestDto request);
}
