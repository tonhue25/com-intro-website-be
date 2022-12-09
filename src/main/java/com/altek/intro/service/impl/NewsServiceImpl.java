package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.News;
import com.altek.intro.entity.NewsTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.repository.NewsTranslateRepository;
import com.altek.intro.service.NewsService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
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

    public void checkNullnewsEntity(NewsTranslate newsTranslate){
        if(newsTranslate == null){
            throw new ResourceNotFoundException(String.format("newsId.not.found"));
        }
    }
    public BaseResponse findNewsById(String language, Long newsId){
        try{
            NewsTranslate newsEntity = newsTranslateRepository.findByNewsId(language,newsId);
            checkNullnewsEntity(newsEntity);
            NewsResponseDto newsResponseDto = new NewsResponseDto();
            newsResponseDto = (NewsResponseDto) newsMapper.convertToDTO(newsResponseDto, newsEntity);
            return new BaseResponse(Constant.SUCCESS, "get.news.by.Id", newsResponseDto);
        } catch (Exception e){
            e.printStackTrace();
            return new BaseResponse(Constant.FAIL, e.getMessage());
        }
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
    public BaseResponse getList(BaseRequest requestDto) {
        ListResponseDto<NewsResponseDto> response = new ListResponseDto<>();
        List<NewsResponseDto> listResponse = new ArrayList<>();
        int pageNumber = 0;
        int size = 0;
        int recordPerPage = 0;
        int totalPages = 0;
        if (DataUtil.isEmpty(requestDto.getPage()) || DataUtil.isEmpty(requestDto.getSize())) {
            listResponse = newsTranslateRepository.getList(requestDto.getSearch(), requestDto.getLanguage(),
                    requestDto.getStartDate(), requestDto.getEndDate());
            if(listResponse.size() > 0){
                size = recordPerPage = listResponse.size();
                pageNumber = totalPages = 1;
            }
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
            Page<NewsResponseDto> pageEntity = newsTranslateRepository.getList(requestDto.getSearch(), requestDto.getLanguage(), requestDto.getStartDate(),
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
        response.setList(listResponse);
        response.setPage(pageNumber);
        response.setSize(size);
        response.setTotalPages(totalPages);
        response.setRecordPerPage(recordPerPage);
        response.setLanguage(requestDto.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.news", response);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse createNews (NewsRequestDto request) {
        log.info("#NewsServiceImpl.createNews   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            News dtoCheck = newsRepository.findNewsById(request.getId());
            if(Objects.nonNull(dtoCheck) && !dtoCheck.getId().equals(request.getId())){
                reponse.setMessage("common.error.code.already");
                reponse.setHttp_code(HttpStatus.BAD_REQUEST.toString());
                return reponse;
            }

            News newsEntity = new News();
            newsEntity = insertRequestToNews(newsEntity ,request);
            newsEntity = newsRepository.save(newsEntity);

            NewsTranslate newsTranslateEntity = new NewsTranslate();
            newsTranslateEntity = insertRequestToNewsTranslate(newsTranslateEntity, request, newsEntity);
            newsTranslateEntity = newsTranslateRepository.save(newsTranslateEntity);

            NewsResponseDto data = new NewsResponseDto();
            data = (NewsResponseDto) newsMapper.convertToDTO(data, newsEntity);
            data = (NewsResponseDto) newsMapper.convertToDTO(data, newsTranslateEntity);
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
            reponse.setHttp_code(HttpStatus.CREATED.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#NewsServiceImpl.createNews " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse updateNews(NewsRequestDto request) {
        log.info("#NewsServiceImpl.updateNews   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            News dtoCheck = newsRepository.findNewsById(request.getId());
            if(!Objects.nonNull(dtoCheck)){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            News newsEntity = new News();
            newsEntity = insertRequestToNews(newsEntity ,request);
            newsEntity = newsRepository.save(newsEntity);

            NewsTranslate newsTranslateEntity = newsTranslateRepository
                    .findByNewsIdAndLanguageId(request.getId(), request.getLanguageId());
            if(newsTranslateEntity == null){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }
            newsTranslateEntity = insertRequestToNewsTranslate(newsTranslateEntity, request, newsEntity);
            newsTranslateEntity = newsTranslateRepository.save(newsTranslateEntity);

            NewsResponseDto data = new NewsResponseDto();
            data = (NewsResponseDto) newsMapper.convertToDTO(data, newsEntity);
            data = (NewsResponseDto) newsMapper.convertToDTO(data, newsTranslateEntity);
            reponse.setData(data);
            reponse.setMessage("common.update.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#NewsServiceImpl.updateNews " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse deleteNews(NewsRequestDto request) {
        log.info("#NewsServiceImpl.deleteNews   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            News newsEntity = newsRepository.findNewsById(request.getId());
            if(!Objects.nonNull(newsEntity)){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            newsEntity.setStatus(Constant.DELETE);
            newsEntity = newsRepository.save(newsEntity);

            List<NewsTranslate> findByNewsId = newsTranslateRepository.
                    findNewsTranslateByNewsId(newsEntity.getId());
            for(NewsTranslate item: findByNewsId){
                item.setStatus(Constant.DELETE);
                newsTranslateRepository.save(item);
            }

            NewsResponseDto data = new NewsResponseDto();
            data = (NewsResponseDto) newsMapper.convertToDTO(data, newsEntity);
            reponse.setData(data);
            reponse.setMessage("common.delete.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#NewsServiceImpl.deleteNews " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    private News insertRequestToNews(News newsEntity, NewsRequestDto request){
        newsEntity = (News) newsMapper.convertToEntity(request, newsEntity);
        newsEntity.setStatus(Constant.INSERT);
        return newsEntity;
    }
    private NewsTranslate insertRequestToNewsTranslate(NewsTranslate newsTranslateEntity,
                                                       NewsRequestDto request, News newsEntity) {
        newsTranslateEntity.setThumbnail(request.getThumbnail());
        newsTranslateEntity.setTitle(request.getTitle());
        newsTranslateEntity.setThumbnail(request.getThumbnail());
        newsTranslateEntity.setShortDescription(request.getShortDescription());
        newsTranslateEntity.setDetail(request.getDetail());
        newsTranslateEntity.setLanguageId(request.getLanguageId());
        newsTranslateEntity.setStatus(Constant.INSERT);
        newsTranslateEntity.setNews(newsEntity);
        return newsTranslateEntity;
    }
}
