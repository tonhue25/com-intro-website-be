package com.altek.intro.services;

import com.altek.intro.dto.request.NewsDetailRequestDTO;

public interface NewsDetailService extends AbstractService{
    NewsDetailRequestDTO getNewsDetailByNewsId(long id);
}
