package com.altek.intro.controller;


import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.NewsResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping("/list")
    public ResponseEntity<NewsResponseDTO> list(@RequestBody ListRequestDto requestDto) {
        try {
            ListResponseDto<NewsResponseDTO> response = newsService.getList(requestDto);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
