package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.RecruitmentResponseDto;
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
        return new ResponseEntity(recruitmentService.getList(requestDto), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RecruitmentResponseDto> create(@RequestBody RecruitmentRequestDto request){
        try {
            RecruitmentResponseDto result = recruitmentService.create(request);
            return new ResponseEntity<RecruitmentResponseDto>(result,HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RecruitmentResponseDto> delete(@PathVariable("id") Long id){
        RecruitmentResponseDto result = recruitmentService.delete(id);
        return new ResponseEntity<RecruitmentResponseDto>(result, HttpStatus.OK);
    }
}
