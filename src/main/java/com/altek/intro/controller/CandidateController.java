package com.altek.intro.controller;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.CandidateResponseDto;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
<<<<<<< HEAD
@RequestMapping("/candidate")
@Slf4j
public class CandidateController {

=======
@RequestMapping("/candidates")
@Slf4j
public class CandidateController {
>>>>>>> tonhue
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<CandidateResponseDto> listAll() {
        try {
            List<CandidateResponseDto> response = candidateService.getAll();
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<CandidateResponseDto> create(@RequestBody CandidateRequestDto request){
        try {
            CandidateResponseDto result = candidateService.create(request);
            return new ResponseEntity<CandidateResponseDto>(result,HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
