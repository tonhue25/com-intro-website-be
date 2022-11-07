package com.altek.intro.services;

import com.altek.intro.dto.NewsDetailDTO;

public interface NewsDetailService extends AbstractService{
    NewsDetailDTO getNewsDetailByNewsId(long id);
}
