package com.altek.intro.controller;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.RecruitmentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitment-types")
@Slf4j
public class RecruitmentTypeController {

    @Autowired
    private RecruitmentTypeService recruitmentTypeService;

    @GetMapping
    public ResponseEntity<BaseResponse> getListRecruitmentType(@RequestParam("language") String language) {
        try {
            return new ResponseEntity(recruitmentTypeService.getListRecruitmentType(language), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createOrUpdateRecruitmentType(@RequestBody RecruitmentTypeTranslateRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(recruitmentTypeService.createOrUpdateRecruitmentType(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteRecruitmentType(@RequestParam Long id) {
        try {
            return new ResponseEntity<BaseResponse>(recruitmentTypeService.deleteRecruitmentType(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
