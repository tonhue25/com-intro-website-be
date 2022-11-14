package com.altek.intro.services;

import com.altek.intro.dto.request.NewsDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsDetailResponseDTO;

public interface NewsDetailService extends AbstractService{
    BaseResponse getNewsDetailByNewsId(long id);

    NewsDetailResponseDTO create(NewsDetailRequestDTO request);

    NewsDetailResponseDTO delete(Long id);

}
