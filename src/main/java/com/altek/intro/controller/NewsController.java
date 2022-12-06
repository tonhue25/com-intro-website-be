package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.service.MenuService;
import com.altek.intro.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/findNewsById")
    public ResponseEntity<BaseResponse> findNewsById(@RequestHeader("Accept-Language") String language,@RequestParam("newsId") Long newsId) {
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
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getListNews(@RequestBody BaseRequest requestDto) {
        try {
            return new ResponseEntity(newsService.getListNews(requestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
