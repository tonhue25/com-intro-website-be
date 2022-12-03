package com.altek.intro.controller;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.service.MenuService;
import com.altek.intro.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menus")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<BaseResponse> listAll(@RequestHeader("Accept-Language") String lang) {
        try {
            return new ResponseEntity(menuService.getListMenu(lang), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
