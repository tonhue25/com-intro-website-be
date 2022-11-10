package com.altek.intro.services;


import java.util.List;

import com.altek.intro.dto.response.NewsResponseDTO;

public interface NewsService extends AbstractService{
    List<NewsResponseDTO> getAll();
}
