package com.altek.intro.services.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.NewsResponseDto;
import com.altek.intro.entities.News;
import com.altek.intro.entities.NewsTranslate;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.repository.NewsTranslateRepository;
import com.altek.intro.services.NewsService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
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
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl extends AbstractServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ListResponseMapper<NewsResponseDto, News> listResponseMapper;

    @Autowired
    private NewsTranslateRepository newsTranslateRepository;

    @Override
    public BaseResponse getList(BaseRequest requestDto) {
        ListResponseDto<NewsResponseDto> response = new ListResponseDto<>();
        List<NewsResponseDto> listResponse = new ArrayList<>();
        NewsResponseDto dto = new NewsResponseDto();
        int pageNumber = 1;
        int size = 0;
        int recordPerPage = 0;
        int totalPages = 1;
        if (DataUtil.isEmpty(requestDto.getPage()) || DataUtil.isEmpty(requestDto.getSize())) {
            listResponse = newsTranslateRepository.getAll(requestDto.getSearch(), requestDto.getLanguage());
            size = recordPerPage = listResponse.size();
        } else {
            Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
            if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
                Sort.Direction sort = Sort.Direction.ASC;
                if (requestDto.getSortType().equals("DESC")) {
                    sort = Sort.Direction.DESC;
                }
                pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(),
                        Sort.by(sort, requestDto.getSortBy()));
            }
            Page<NewsResponseDto> pageEntity = newsTranslateRepository.getList(requestDto.getSearch(), requestDto.getLanguage(),
                    pageable);
            listResponse = pageEntity.getContent();
            size = pageEntity.getNumberOfElements();
            recordPerPage = pageable.getPageSize();
            totalPages = pageEntity.getTotalPages();
            pageNumber = pageable.getPageNumber();
            if (pageEntity.getTotalPages() > 0) {
                pageNumber = pageNumber + 1;
            }
        }
        if (CollectionUtils.isNotEmpty(listResponse)) {
            response.setList(listResponse);
            response.setPage(pageNumber);
            response.setSize(size);
            response.setTotalPages(totalPages);
            response.setRecordPerPage(recordPerPage);
        }
        response.setLanguage(requestDto.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.news", response);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsResponseDto create(NewsRequestDto request) {
        News entity = new News();
        entity = (News) newsMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = newsRepository.save(entity);
        NewsResponseDto response = modelMapper.map(entity, NewsResponseDto.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsResponseDto delete(Long id) {
        Optional<News> optional = newsRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("News.not.found.with.id:%s", id));
        }
        News entity = optional.get();
        entity.setStatus(Constant.DELETE);
        newsRepository.save(entity);
        NewsResponseDto response = modelMapper.map(entity, NewsResponseDto.class);
        return response;
    }

    @Override
    public BaseResponse getListNew(BaseRequest requestDto) {
        ListResponseDto<NewsResponseDto> response = new ListResponseDto<>();
        List<NewsResponseDto> listResponse = new ArrayList<>();
        int pageNumber = 1;
        int size = 0;
        int recordPerPage = 0;
        int totalPages = 1;
        if (DataUtil.isEmpty(requestDto.getPage()) || DataUtil.isEmpty(requestDto.getSize())) {
            listResponse = newsTranslateRepository.getAllNewsNew(requestDto.getSearch(), requestDto.getLanguage(),
                    requestDto.getStartDate(), requestDto.getEndDate());
            size = recordPerPage = listResponse.size();
        } else {
            Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
            if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
                Sort.Direction sort = Sort.Direction.ASC;
                if (requestDto.getSortType().equals("DESC")) {
                    sort = Sort.Direction.DESC;
                }
                pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(),
                        Sort.by(sort, requestDto.getSortBy()));
            }
            Page<NewsResponseDto> pageEntity = newsTranslateRepository.getListNewsNew(requestDto.getSearch(), requestDto.getLanguage(), requestDto.getStartDate(),
                    requestDto.getEndDate(),
                    pageable);
            listResponse = pageEntity.getContent();
            size = pageEntity.getNumberOfElements();
            recordPerPage = pageable.getPageSize();
            totalPages = pageEntity.getTotalPages();
            pageNumber = pageable.getPageNumber();
            if (pageEntity.getTotalPages() > 0) {
                pageNumber = pageNumber + 1;
            }
        }
        if (CollectionUtils.isNotEmpty(listResponse)) {
            response.setList(listResponse);
            response.setPage(pageNumber);
            response.setSize(size);
            response.setTotalPages(totalPages);
            response.setRecordPerPage(recordPerPage);
        }
        response.setLanguage(requestDto.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.news.new", response);
    }
}
