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
            return new ResponseEntity(menuService.getAll(lang), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody MenuRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(menuService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = new BaseResponse(Constant.FAIL,
                    "ex.common.system.error.");
            return new ResponseEntity<BaseResponse>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            return new ResponseEntity<BaseResponse>(menuService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(new BaseResponse(Constant.FAIL,
                    "ex.common.system.error."), HttpStatus.OK);
        }
    }
}
