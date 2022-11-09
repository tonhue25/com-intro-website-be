package com.altek.intro.controller;

import com.altek.intro.dto.request.NewsDetailRequestDTO;
import com.altek.intro.services.NewsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newsDetail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NewsDetailController {

    @Autowired
    private NewsDetailService newsDetailService;

    @GetMapping
    public ResponseEntity<NewsDetailRequestDTO> getNewsDetailByNewsId(@RequestParam("newsId") long newsId){
        return new ResponseEntity<>(newsDetailService.getNewsDetailByNewsId(newsId), HttpStatus.OK);
    }
}
