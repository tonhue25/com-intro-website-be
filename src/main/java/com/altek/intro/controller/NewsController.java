package com.altek.intro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altek.intro.dto.response.NewsResponseDTO;
import com.altek.intro.services.NewsService;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<NewsResponseDTO> listAll() {
        List<NewsResponseDTO> response = newsService.getAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
