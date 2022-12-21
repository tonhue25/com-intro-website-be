package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exception.AuthorizedException;
import com.altek.intro.exception.ParameterIllegalException;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.service.MenuService;
import com.altek.intro.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/findNewsById")
    public ResponseEntity<BaseResponse> findNewsById(@RequestHeader("Accept-Language") String language, @RequestParam("newsId") Long newsId) {
        try {
            return new ResponseEntity(newsService.findNewsById(language, newsId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/navbar")
    public ResponseEntity<BaseResponse> getNavNews(@RequestParam("language") String language,
                                                   @RequestParam("parentId") Long parentId) {
        try {
            return new ResponseEntity(menuService.getNavNews(language, parentId), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getList(@RequestBody @Valid BaseRequest requestDto) {
        try {
            return new ResponseEntity(newsService.getNews(requestDto), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createNews(@RequestBody NewsRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            response = newsService.createNews(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ParameterIllegalException ex) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (AuthorizedException ex) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateNews(@RequestBody NewsRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#NewsController.updateNews: START -- ");
            response = newsService.updateNews(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParameterIllegalException ex) {
            ex.printStackTrace();
            log.error("#NewsController.updateNews ParameterIllegalException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#NewsController.updateNews AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#NewsController.updateNews SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody NewsRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#NewsController.deleteNews: START -- ");
            response = newsService.deleteNews(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            ex.printStackTrace();
            log.error("#NewsController.deleteNews ResourceNotFoundException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.NOT_FOUND.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#NewsController.deleteNews AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#NewsController.deleteNews SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
