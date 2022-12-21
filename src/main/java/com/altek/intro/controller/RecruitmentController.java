package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/recruitments")
@Slf4j
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @PostMapping("list")
    public ResponseEntity<BaseResponse> list(@RequestBody @Valid BaseRequest requestDto) {
        return new ResponseEntity(recruitmentService.getRecruitments(requestDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createOrUpdateRecruitment(@RequestBody RecruitmentRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(recruitmentService.createOrUpdateRecruitment(request), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteRecruitment(@PathVariable Long id) {
        try {
            return new ResponseEntity<BaseResponse>(recruitmentService.deleteRecruitment(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
