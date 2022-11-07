package com.altek.intro.services.impl;

import com.altek.intro.dto.NewsDetailDTO;
import com.altek.intro.entites.NewsDetailEntity;
import com.altek.intro.entites.NewsEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.NewsDetailMapper;
import com.altek.intro.repository.NewsDetailRepository;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.services.NewsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsDetailServiceImpl extends AbstractServiceImpl implements NewsDetailService {

    @Autowired
    private NewsDetailRepository newsDetailRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsDetailMapper newsDetailMapper;

    @Override
    public NewsDetailDTO getNewsDetailByNewsId(long id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("news.not.found.with.id:%s", id));
        }
        NewsDetailDTO pageDetailDTO = new NewsDetailDTO();
        Optional<NewsDetailEntity> optionalNewsDetail = newsDetailRepository.findByNewsId(id);
        if(!optionalNewsDetail.isPresent()){
            throw new ResourceNotFoundException(String.format("news.not.have.news.detail.id:%s", id));
        }
        NewsDetailDTO dto = (NewsDetailDTO) newsDetailMapper.convertToDTO(pageDetailDTO,optionalNewsDetail.get());
        return dto;
    }
}