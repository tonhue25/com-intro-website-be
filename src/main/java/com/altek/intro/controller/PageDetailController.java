package com.altek.intro.controller;

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

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageDetailResponseDTO;
import com.altek.intro.services.PageDetailService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

@RestController
@RequestMapping("/pageDetail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageDetailController.class);

    @Autowired
    private PageDetailService pageDetailService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/{id}")
    public ResponseEntity<PageDetailResponseDTO> listPageContentByMenuId(@PathVariable long id) {
        return new ResponseEntity<>(pageDetailService.getByPageContentId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody PageDetailRequestDTO request) {
        try {
            return new ResponseEntity<BaseResponse>(pageDetailService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
            return new ResponseEntity<BaseResponse>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            return new ResponseEntity<BaseResponse>(pageDetailService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error."), HttpStatus.OK);
        }
    }
}
