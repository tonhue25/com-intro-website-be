package com.altek.intro.controller;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.services.PageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageContentController {
    @Autowired
    PageContentService pageContentService;

    @GetMapping("/")
    public ResponseEntity<PageContentDTO> listAll() {
        try {
            List<PageContentDTO> response = pageContentService.getAllPageContent();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }
}
