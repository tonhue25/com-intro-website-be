package com.altek.intro.controller;

import com.altek.intro.dto.HomeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<HomeDTO> listAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
