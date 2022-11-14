package com.altek.intro.services;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDTO;

public interface NewsService extends AbstractService{

    BaseResponse getList(ListRequestDto request);
    NewsResponseDTO create(NewsRequestDto request);
    NewsResponseDTO delete(Long id);
}
