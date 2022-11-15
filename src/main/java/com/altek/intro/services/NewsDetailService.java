package com.altek.intro.services;

import com.altek.intro.dto.request.NewsDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsDetailResponseDto;

public interface NewsDetailService extends AbstractService{
    BaseResponse getNewsDetailByNewsId(long id);

    NewsDetailResponseDto create(NewsDetailRequestDto request);

    NewsDetailResponseDto delete(Long id);

}
