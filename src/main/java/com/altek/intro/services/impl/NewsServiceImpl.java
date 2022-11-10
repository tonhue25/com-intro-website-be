package com.altek.intro.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.response.NewsResponseDTO;
import com.altek.intro.entites.NewsEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.services.NewsService;

@Service
public class NewsServiceImpl extends AbstractServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<NewsResponseDTO> getAll() {
        try {
            List<NewsResponseDTO> listDto = new ArrayList<>();
            List<NewsEntity> listEntity = newsRepository.findAll();
            NewsResponseDTO dto = new NewsResponseDTO();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (NewsResponseDTO) newsMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return listDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
