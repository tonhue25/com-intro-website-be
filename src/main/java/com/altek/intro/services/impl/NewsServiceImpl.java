package com.altek.intro.services.impl;

import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.entites.NewsEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.services.NewsService;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl extends AbstractServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NewsRequestDTO> getAll() {
        try {
            List<NewsRequestDTO> listDto = new ArrayList<>();
            List<NewsEntity> listEntity = newsRepository.findAll();
            NewsRequestDTO dto = new NewsRequestDTO();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (NewsRequestDTO) newsMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return listDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsRequestDTO Create(NewsRequestDTO request) {
        NewsEntity entity = new NewsEntity();
        entity = (NewsEntity) newsMapper.convertToEntity(request,entity);
        entity = newsRepository.save(entity);
        NewsRequestDTO response = modelMapper.map(entity, NewsRequestDTO.class);
        return response;
    }
}
