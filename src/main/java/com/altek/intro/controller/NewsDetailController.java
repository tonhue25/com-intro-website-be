package com.altek.intro.controller;

import com.altek.intro.dto.NewsDTO;
import com.altek.intro.dto.NewsDetailDTO;
import com.altek.intro.dto.PageDetailDTO;
import com.altek.intro.services.NewsDetailService;
import com.altek.intro.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
