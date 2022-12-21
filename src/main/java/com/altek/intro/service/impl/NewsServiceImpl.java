package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.NewsResponseDto;
import com.altek.intro.entity.News;
import com.altek.intro.entity.NewsTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.repository.NewsTranslateRepository;
import com.altek.intro.service.NewsService;
import com.altek.intro.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
    private NewsTranslateRepository newsTranslateRepository;

    public void checkNullnewsEntity(NewsTranslate newsTranslate) {
        if (newsTranslate == null) {
            throw new ResourceNotFoundException(String.format("newsId.not.found"));
        }
    }

    public BaseResponse findNewsById(String language, Long newsId) {
        try {
            NewsTranslate newsEntity = newsTranslateRepository.findByNewsId(language, newsId);
            checkNullnewsEntity(newsEntity);
            NewsResponseDto newsResponseDto = new NewsResponseDto();
            newsResponseDto = (NewsResponseDto) newsMapper.convertToDTO(newsResponseDto, newsEntity);
            return new BaseResponse(Constant.SUCCESS, "get.news.by.Id", newsResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(Constant.FAIL, e.getMessage());
        }
    }

    @Override
    public BaseResponse getNews(BaseRequest requestDto) {
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

    private Long addNewTranslationForNewsAndReturnId(NewsRequestDto request, News newsEntity) {
        NewsTranslate newsTranslateEntity = new NewsTranslate();
        newsTranslateEntity = insertRequestToNewsTranslate(newsTranslateEntity, request, newsEntity);
        newsTranslateEntity = newsTranslateRepository.save(newsTranslateEntity);
        return newsTranslateEntity.getNews().getId();
    }

    private Long newNewsAndReturnId(NewsRequestDto request) {
        News newsEntity = new News();
        newsEntity = insertRequestToNews(newsEntity, request);
        newsEntity = newsRepository.save(newsEntity);

        addNewTranslationForNewsAndReturnId(request, newsEntity);
        return newsEntity.getId();
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse createNews(NewsRequestDto request) {
        log.info("#NewsServiceImpl.createNews   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            NewsResponseDto dtoCheckExist = newsTranslateRepository
                    .findByNewsIdAndLanguageId(request.getId(), request.getLanguageId());
            if (Objects.nonNull(dtoCheckExist)) {
                reponse.setMessage("common.error.code.already");
                reponse.setHttp_code(HttpStatus.BAD_REQUEST.toString());
                return reponse;
            }

            long idResponse;
            News dtoCheckAddTranslationOrNew = newsRepository.findNewsById(request.getId());
            if (Objects.nonNull(dtoCheckAddTranslationOrNew)) {
                idResponse = addNewTranslationForNewsAndReturnId(request, dtoCheckAddTranslationOrNew);
            } else {
                idResponse = newNewsAndReturnId(request);
            }

            NewsResponseDto data = newsTranslateRepository
                    .findByNewsIdAndLanguageId(idResponse, request.getLanguageId());
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
            reponse.setHttp_code(HttpStatus.CREATED.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#NewsServiceImpl.createNewNews " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse updateNews(NewsRequestDto request) {
        log.info("#NewsServiceImpl.updateNews   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            NewsResponseDto dtoCheckExist = newsTranslateRepository
                    .findByNewsIdAndLanguageId(request.getId(), request.getLanguageId());
            News newsEntity = new News();
            if (Objects.isNull(dtoCheckExist)) {
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            } else {
                newsEntity = insertRequestToNews(newsEntity, request);
                newsEntity = newsRepository.save(newsEntity);

                NewsTranslate newsTranslateEntity = newsTranslateRepository.findNewsTranslateByNewsIdAndLanguageId(request.getId(), request.getLanguageId());
                newsTranslateEntity = insertRequestToNewsTranslate(newsTranslateEntity, request, newsEntity);
                newsTranslateRepository.save(newsTranslateEntity);
            }

            NewsResponseDto data = newsTranslateRepository
                    .findByNewsIdAndLanguageId(newsEntity.getId(), request.getLanguageId());
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
            if (!Objects.nonNull(newsEntity)) {
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }
            newsEntity.setStatus(Constant.DELETE);
            newsEntity = newsRepository.save(newsEntity);
            List<NewsTranslate> findByNewsId = newsTranslateRepository.
                    findNewsTranslateByNewsId(newsEntity.getId());
            for (NewsTranslate item : findByNewsId) {
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

    private News insertRequestToNews(News newsEntity, NewsRequestDto request) {
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
