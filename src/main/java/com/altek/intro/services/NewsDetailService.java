package com.altek.intro.services;

import com.altek.intro.dto.request.NewsDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsDetailResponseDTO;

public interface NewsDetailService extends AbstractService{
    BaseResponse getNewsDetailByNewsId(long id);

    NewsDetailResponseDTO create(NewsDetailRequestDto request);

    NewsDetailResponseDTO delete(Long id);

}
