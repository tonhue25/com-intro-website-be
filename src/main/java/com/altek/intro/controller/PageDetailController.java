package com.altek.intro.controller;

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageDetailResponseDTO;
import com.altek.intro.services.PageDetailService;
import com.altek.intro.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pageDetail")
@Slf4j
public class PageDetailController {


    @Autowired
    private PageDetailService pageDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> listPageContentByMenuId(@PathVariable long id) {
        return new ResponseEntity<>(pageDetailService.getByPageContentId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody PageDetailRequestDTO request) {
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
