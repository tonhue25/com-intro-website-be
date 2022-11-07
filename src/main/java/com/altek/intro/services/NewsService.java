package com.altek.intro.services;

import com.altek.intro.dto.NewsDTO;

import java.util.List;

public interface NewsService extends AbstractService{
    List<NewsDTO> getAll();
}
