package com.altek.intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altek.intro.dto.NewsDetailDTO;
import com.altek.intro.services.NewsDetailService;

@RestController
@RequestMapping("/newsDetail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NewsDetailController {

    @Autowired
    private NewsDetailService newsDetailService;

    @GetMapping
    public ResponseEntity<NewsDetailDTO> getNewsDetailByNewsId(@RequestParam("newsId") long newsId){
        return new ResponseEntity<>(newsDetailService.getNewsDetailByNewsId(newsId), HttpStatus.OK);
    }
}
