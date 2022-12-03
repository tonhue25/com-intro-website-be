package com.altek.intro.controller;

import com.altek.intro.dto.request.ProductGroupRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.ProductGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productgroups")
@Slf4j
public class ProductGroupController {

    @Autowired
    private ProductGroupService productGroupService;

    @GetMapping
    public ResponseEntity<BaseResponse> getListProductGroup() {
        try {
            return new ResponseEntity(productGroupService.getListProductGroup(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createOrUpdate(@RequestBody ProductGroupRequestDto dto) {
        try {
            return new ResponseEntity(productGroupService.createOrUpdate(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try {
            return new ResponseEntity(productGroupService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
