package com.altek.intro.controller;

import com.altek.intro.dto.request.PageDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.PageDetailService;
import com.altek.intro.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pageDetails")
@Slf4j
public class PageDetailController {
    @Autowired
    private PageDetailService pageDetailService;

    @GetMapping
    public ResponseEntity<BaseResponse> getPage(@RequestParam("page") Long page) {
        return new ResponseEntity<>(pageDetailService.getByPage(page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody PageDetailRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(pageDetailService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = new BaseResponse(Constant.FAIL,
                    ex.getMessage());
            return new ResponseEntity<BaseResponse>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            return new ResponseEntity<BaseResponse>(pageDetailService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(new BaseResponse(Constant.FAIL,
                    ex.getMessage()), HttpStatus.OK);
        }
    }
}
