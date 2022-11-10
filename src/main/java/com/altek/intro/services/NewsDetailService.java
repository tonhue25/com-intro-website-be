package com.altek.intro.services;

import com.altek.intro.dto.response.NewsDetailResponseDTO;

public interface NewsDetailService extends AbstractService{
    NewsDetailResponseDTO getNewsDetailByNewsId(long id);
}
