package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.NewsResponseDto;
import com.altek.intro.entity.News;
import com.altek.intro.entity.NewsTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.repository.NewsTranslateRepository;
import com.altek.intro.service.NewsService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl extends AbstractServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NewsTranslateRepository newsTranslateRepository;

    public void checkNullNewsEntity(NewsTranslate newsTranslate) {
        if (newsTranslate == null) {
            throw new ResourceNotFoundException(String.format("newsId.not.found"));
        }
    }

    public BaseResponse findNewsById(String language, Long newsId) {
        try {
            NewsTranslate newsEntity = newsTranslateRepository.findByNewsId(language, newsId);
            checkNullNewsEntity(newsEntity);
            NewsResponseDto newsResponseDto = new NewsResponseDto();
            newsResponseDto = (NewsResponseDto) newsMapper.convertToDTO(newsResponseDto, newsEntity);
            return new BaseResponse(Constant.SUCCESS, "get.news.by.Id", newsResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(Constant.FAIL, e.getMessage());
        }
    }

    @Override
    public BaseResponse getList(BaseRequest requestDto) {
        Pageable pageable = getPageable(requestDto);
        Page<NewsResponseDto> page = newsTranslateRepository.getList(requestDto.getSearch(), requestDto.getLanguage(), requestDto.getStartDate(),
                requestDto.getEndDate(),
                pageable);
        if (!CollectionUtils.isNotEmpty(page.getContent())) {
            return new BaseResponse(Constant.SUCCESS, "get.list.news", Constant.EMPTY_LIST);
        }
        ListResponseDto<NewsResponseDto> response = new ListResponseDto<>(pageable, page, page.getContent(), requestDto.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.news", response);
    }
}
