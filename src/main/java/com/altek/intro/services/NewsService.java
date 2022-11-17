package com.altek.intro.services;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDto;

public interface NewsService extends AbstractService{

    BaseResponse getList(ListRequestDto request);
    NewsResponseDto create(NewsRequestDto request);
    NewsResponseDto delete(Long id);
}
