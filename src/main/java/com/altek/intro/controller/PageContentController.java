package com.altek.intro.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altek.intro.dto.request.PageContentRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageContentResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.PageContentService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

@RestController
@RequestMapping("/page")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageContentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageContentController.class);

    @Autowired
    private PageContentService pageContentService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping
    public ResponseEntity<PageContentResponseDTO> listAll() {
        try {
            List<PageContentResponseDTO> response = pageContentService.getAll();
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
    public ResponseEntity<List<PageContentResponseDTO>> listPageContentByMenuId(@PathVariable Long id){
         return new ResponseEntity<>(pageContentService.listPageContentByMenuId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody PageContentRequestDTO request) {
        try {
            return new ResponseEntity<BaseResponse>(pageContentService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
            return new ResponseEntity<BaseResponse>(result, HttpStatus.CREATED);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            return new ResponseEntity<BaseResponse>(pageContentService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error."), HttpStatus.OK);
        }
    }
}

