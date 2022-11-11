package com.altek.intro.controller;

import java.util.List;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<NewsResponseDTO> list(@RequestBody ListRequestDto requestDto) {
        try {
            ListResponseDto<NewsResponseDTO> response = newsService.getList(requestDto);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
