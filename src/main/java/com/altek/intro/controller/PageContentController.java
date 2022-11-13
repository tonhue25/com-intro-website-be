package com.altek.intro.controller;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.PageContentRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageContentResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.PageContentService;
import com.altek.intro.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
@Slf4j
public class PageContentController {

    @Autowired
    private PageContentService pageContentService;

    @GetMapping
    public ResponseEntity<PageContentResponseDTO> listAll() {
        try {
            List<PageContentResponseDTO> response = pageContentService.getAll();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PageContentResponseDTO>> listPageContentByMenuId(@PathVariable Long id) {
        return new ResponseEntity<>(pageContentService.listPageContentByMenuId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody PageContentRequestDTO request) {
        try {
            return new ResponseEntity<BaseResponse>(pageContentService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = new BaseResponse(Constant.FAIL,
                    ex.getMessage());
            return new ResponseEntity<BaseResponse>(result, HttpStatus.CREATED);
        }
    }

    @PostMapping("list")
    public ResponseEntity<BaseResponse> listPageContent(@RequestBody ListRequestDto requestDto){
        return new ResponseEntity<BaseResponse>(pageContentService.listPageContent(requestDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            return new ResponseEntity<BaseResponse>(pageContentService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(new BaseResponse(Constant.FAIL,
                    ex.getMessage()), HttpStatus.OK);
        }
    }
}

