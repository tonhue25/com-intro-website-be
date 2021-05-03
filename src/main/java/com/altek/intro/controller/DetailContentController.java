package com.altek.intro.controller;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.dto.DetailContentDTO;
import com.altek.intro.entites.DetailContentEntity;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.DetailContentService;
import com.altek.intro.services.PageContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detailPage")
public class DetailContentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailContentController.class);
    @Autowired
    DetailContentService detailContentService;

    @GetMapping("/")
    public ResponseEntity<DetailContentDTO> listAll() {
        try {
            List<DetailContentDTO> response = detailContentService.getAllDetailContent();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/content/{contentId}")
    public ResponseEntity<DetailContentDTO> listAllByContentId(@PathVariable Long contentId) {
        try {
            List<DetailContentDTO> response = detailContentService.getAllDetailContentByContentId(contentId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetailContentEntity> getPageContentById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(detailContentService.getDetailContentById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
