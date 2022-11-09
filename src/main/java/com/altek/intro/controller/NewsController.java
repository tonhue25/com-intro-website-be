package com.altek.intro.controller;

import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.services.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NewsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<NewsRequestDTO> listAll() {
        List<NewsRequestDTO> response = newsService.getAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsRequestDTO> Create(@RequestBody NewsRequestDTO request){
        try{
            NewsRequestDTO result = newsService.Create(request);
            return new ResponseEntity<NewsRequestDTO>(request,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
