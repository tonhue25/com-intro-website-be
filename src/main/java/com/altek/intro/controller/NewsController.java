package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.NewsRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDto;
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

    @GetMapping("/navbar")
    public ResponseEntity<BaseResponse> getNav(@RequestParam("language") String language,
                                               @RequestParam("parentId") Long parentId) {
        try {
            return new ResponseEntity(menuService.getNav(language, parentId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getList(@RequestBody BaseRequest requestDto) {
        try {
            return new ResponseEntity(newsService.getList(requestDto), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<NewsResponseDto> Create(@RequestBody NewsRequestDto request) {
        try {
            NewsResponseDto result = newsService.create(request);
            return new ResponseEntity<NewsResponseDto>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsResponseDto> delete(@PathVariable("id") Long id){
        NewsResponseDto result = newsService.delete(id);
        return new ResponseEntity<NewsResponseDto>(result, HttpStatus.OK);
    }
}
