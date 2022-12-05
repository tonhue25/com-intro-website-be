package com.altek.intro.controller;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidates")
@Slf4j
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<BaseResponse> getListCandidate() {
        try {
            return new ResponseEntity(candidateService.getListCandidate(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CandidateRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(candidateService.create(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<BaseResponse>(candidateService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
