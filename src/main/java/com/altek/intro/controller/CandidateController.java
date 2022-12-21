package com.altek.intro.controller;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/candidates")
@Slf4j
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<BaseResponse> getCandidates() {
        try {
            return new ResponseEntity(candidateService.getCandidates(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCandidate(@RequestBody CandidateRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(candidateService.createCandidate(request), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCandidate(@PathVariable Long id) {
        try {
            return new ResponseEntity<BaseResponse>(candidateService.deleteCandidate(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
