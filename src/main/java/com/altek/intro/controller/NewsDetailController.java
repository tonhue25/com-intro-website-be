package com.altek.intro.controller;

import com.altek.intro.dto.request.LeadershipRequestDTO;
import com.altek.intro.dto.request.NewsDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import com.altek.intro.services.NewsDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.altek.intro.dto.response.NewsDetailResponseDTO;
@RestController
@RequestMapping("/newsDetail")
@Slf4j
public class NewsDetailController {

    @Autowired
    private NewsDetailService newsDetailService;

    @GetMapping
    public ResponseEntity<BaseResponse> getNewsDetailByNewsId(@RequestParam("newsId") long newsId){
        return new ResponseEntity<>(newsDetailService.getNewsDetailByNewsId(newsId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsDetailResponseDTO> create(@RequestBody NewsDetailRequestDTO request){
        try {
            NewsDetailResponseDTO result = newsDetailService.create(request);
            return new ResponseEntity<NewsDetailResponseDTO>(result,HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDetailResponseDTO> delete(@PathVariable("id") Long id){
        NewsDetailResponseDTO result = newsDetailService.delete(id);
        return new ResponseEntity<NewsDetailResponseDTO>(result, HttpStatus.OK);
    }

}
