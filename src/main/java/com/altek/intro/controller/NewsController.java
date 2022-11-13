package com.altek.intro.controller;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.NewsResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> list(@RequestBody ListRequestDto requestDto) {
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
    public ResponseEntity<NewsResponseDTO> Create(@RequestBody NewsRequestDTO request) {
        try {
            NewsResponseDTO result = newsService.Create(request);
            return new ResponseEntity<NewsResponseDTO>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
