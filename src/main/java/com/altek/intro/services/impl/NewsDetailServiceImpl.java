package com.altek.intro.services.impl;

import com.altek.intro.dto.request.NewsDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import com.altek.intro.dto.response.NewsDetailResponseDTO;

import com.altek.intro.entities.LeadershipEntity;
import com.altek.intro.entities.NewsDetailEntity;
import com.altek.intro.entities.NewsEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.NewsDetailMapper;
import com.altek.intro.repository.NewsDetailRepository;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.services.NewsDetailService;
import com.altek.intro.utils.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class NewsDetailServiceImpl extends AbstractServiceImpl implements NewsDetailService {

    @Autowired
    private NewsDetailRepository newsDetailRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsDetailMapper newsDetailMapper;

    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsDetailResponseDTO create(NewsDetailRequestDTO request) {
        NewsDetailEntity entity = new NewsDetailEntity();
        entity = (NewsDetailEntity)  newsDetailMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = newsDetailRepository.save(entity);
        NewsDetailResponseDTO response = modelMapper.map(entity, NewsDetailResponseDTO.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsDetailResponseDTO delete(Long id) {
        Optional<NewsDetailEntity> optional = newsDetailRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("Newsdetail.not.found.with.id:%s", id));
        }
        NewsDetailEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        newsDetailRepository.save(entity);
        NewsDetailResponseDTO response = modelMapper.map(entity, NewsDetailResponseDTO.class);
        return response;
    }
}
