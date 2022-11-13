package com.altek.intro.controller;

import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.services.NewsDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
