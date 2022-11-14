package com.altek.intro.controller;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import com.altek.intro.dto.response.NewsResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.NewsService;
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
            NewsResponseDTO result = newsService.create(request);
            return new ResponseEntity<NewsResponseDTO>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> delete(@PathVariable("id") Long id){
        NewsResponseDTO result = newsService.delete(id);
        return new ResponseEntity<NewsResponseDTO>(result, HttpStatus.OK);
    }
}
