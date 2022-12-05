package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitments")
@Slf4j
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @PostMapping("list")
    public ResponseEntity<BaseResponse> list(@RequestBody BaseRequest requestDto) {
        try {
            return new ResponseEntity(recruitmentService.getList(requestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createOrUpdate(@RequestBody RecruitmentRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(recruitmentService.createOrUpdate(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<BaseResponse>(recruitmentService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
