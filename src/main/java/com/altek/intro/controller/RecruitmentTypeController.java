package com.altek.intro.controller;

import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.RecruitmentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitmentTypes")
@Slf4j
public class RecruitmentTypeController {

    @Autowired
    private RecruitmentTypeService recruitmentTypeService;

    @GetMapping
    public ResponseEntity<BaseResponse> listAll(@RequestParam("language") String language) {
        return new ResponseEntity(recruitmentTypeService.getAll(language), HttpStatus.OK);
    }
}
