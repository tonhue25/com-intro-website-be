package com.altek.intro.service.impl;

import com.altek.intro.dto.request.NewsDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsDetailResponseDto;
import com.altek.intro.entity.NewsDetail;
import com.altek.intro.entity.News;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.NewsDetailMapper;
import com.altek.intro.repository.NewsDetailRepository;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.service.NewsDetailService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
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
        Optional<News> optional = newsRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("news.not.found.with.id:%s", id));
        }
        Optional<NewsDetail> optionalNewsDetail = newsDetailRepository.findByNews(optional.get());
        if (!optionalNewsDetail.isPresent()) {
            throw new ResourceNotFoundException(String.format("news.not.have.news.detail.id:%s", id));
        }
        NewsDetailResponseDto response = (NewsDetailResponseDto) newsDetailMapper.convertToDTO(new NewsDetailResponseDto(), optionalNewsDetail.get());
        return new BaseResponse(Constant.SUCCESS, response);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsDetailResponseDto create(NewsDetailRequestDto request) {
        NewsDetail entity = new NewsDetail();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<NewsDetail> optional = newsDetailRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (NewsDetail) newsDetailMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        newsDetailRepository.save(entity);
        NewsDetailResponseDto response = modelMapper.map(entity, NewsDetailResponseDto.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsDetailResponseDto delete(Long id) {
        Optional<NewsDetail> optional = newsDetailRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("Newsdetail.not.found.with.id:%s", id));
        }
        NewsDetail entity = optional.get();
        entity.setStatus(Constant.DELETE);
        newsDetailRepository.save(entity);
        NewsDetailResponseDto response = modelMapper.map(entity, NewsDetailResponseDto.class);
        return response;
    }
}

