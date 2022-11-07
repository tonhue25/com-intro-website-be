package com.altek.intro.controller;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.dto.PageDetailDTO;
import com.altek.intro.services.PageDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagedetail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageDetailController.class);

    @Autowired
    private PageDetailService pageDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<PageDetailDTO> listPageContentByMenuId(@PathVariable long id){
        return new ResponseEntity<>(pageDetailService.getByPageContentId(id), HttpStatus.OK);
    }
}
