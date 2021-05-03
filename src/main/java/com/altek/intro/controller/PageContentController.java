package com.altek.intro.controller;

import com.altek.intro.config.MessageService;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
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
@RequestMapping("/page")
public class PageContentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageContentController.class);

    @Autowired
    PageContentService pageContentService;

    @Autowired
    MessageService messageService;

    @GetMapping("/")
    public ResponseEntity<PageContentDTO> listAll() {
        try {
            List<PageContentDTO> response = pageContentService.getAllPageContent();
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
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<PageContentDTO> listAllByMenuId(@PathVariable int menuId) {
        try {
            List<PageContentDTO> response = pageContentService.getAllPageContentByMenuId(menuId);
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
    public ResponseEntity<PageContentEntity> getPageContentById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(pageContentService.getPageContentById(id), HttpStatus.OK);
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
