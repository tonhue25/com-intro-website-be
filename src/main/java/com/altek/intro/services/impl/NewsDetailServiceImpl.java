package com.altek.intro.services.impl;

import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsDetailResponseDTO;

import com.altek.intro.entities.NewsDetailEntity;
import com.altek.intro.entities.NewsEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.NewsDetailMapper;
import com.altek.intro.repository.NewsDetailRepository;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.services.NewsDetailService;
import com.altek.intro.utils.Constant;
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
    public BaseResponse getNewsDetailByNewsId(long id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("news.not.found.with.id:%s", id));
        }
        Optional<NewsDetailEntity> optionalNewsDetail = newsDetailRepository.findByNews(optional.get());
        if (!optionalNewsDetail.isPresent()) {
            throw new ResourceNotFoundException(String.format("news.not.have.news.detail.id:%s", id));
        }
        NewsDetailResponseDTO response = (NewsDetailResponseDTO) newsDetailMapper.convertToDTO(new NewsDetailResponseDTO(), optionalNewsDetail.get());
        return new BaseResponse(Constant.SUCCESS, response);
    }
}
