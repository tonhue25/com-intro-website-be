package com.altek.intro.controller;

import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.ProductGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productgroups")
@Slf4j
public class ProductGroupController {

    @Autowired
    private ProductGroupService productGroupService;

    @GetMapping
    public ResponseEntity<BaseResponse> listAll() {
        return new ResponseEntity(productGroupService.getAll(), HttpStatus.OK);
    }
}
