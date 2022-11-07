package com.altek.intro.controller;

import com.altek.intro.dto.NewsDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<NewsDTO> listAll() {
        List<NewsDTO> response = newsService.getAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
