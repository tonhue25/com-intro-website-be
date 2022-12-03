package com.altek.intro.controller;

import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
@Slf4j
public class PageController {

    @Autowired
    private PageService pageContentService;

    @GetMapping
    public ResponseEntity<BaseResponse> listAll(@RequestHeader("Accept-Language") String lang) {
        try {
            return new ResponseEntity(pageContentService.getAllPageContent(lang), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findPageById")
    public ResponseEntity<BaseResponse> findAllPageContentByMenuId(@RequestBody PageRequestDto requestBody) {
        try {
            return new ResponseEntity(pageContentService.getAllPageContentByMenuId(requestBody), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

