package com.altek.intro.services;

import com.altek.intro.dto.request.NewsRequestDTO;

import java.util.List;

public interface NewsService extends AbstractService{
    List<NewsRequestDTO> getAll();

    NewsRequestDTO Create(NewsRequestDTO newsRequestDTO);
}
