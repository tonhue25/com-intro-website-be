package com.altek.intro.services;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDTO;

import java.util.List;

public interface NewsService extends AbstractService{

    BaseResponse getList(ListRequestDto request);
    NewsResponseDTO create(NewsRequestDTO request);
    NewsResponseDTO delete(Long id);
}
