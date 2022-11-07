package com.altek.intro.controller;

import com.altek.intro.config.MessageService;
import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.PageContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageContentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageContentController.class);

    @Autowired
    private PageContentService pageContentService;

    @GetMapping
    public ResponseEntity<PageContentDTO> listAll() {
        try {
            List<PageContentDTO> response = pageContentService.getAll();
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<PageContentDTO>> listPageContentByMenuId(@PathVariable Long id){
         return new ResponseEntity<>(pageContentService.listPageContentByMenuId(id), HttpStatus.OK);
    }
}
