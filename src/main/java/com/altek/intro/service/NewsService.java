package com.altek.intro.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDto;

public interface NewsService extends AbstractService{
    NewsResponseDto create(NewsRequestDto request);
    NewsResponseDto delete(Long id);
    BaseResponse getList(BaseRequest requestDto);
}