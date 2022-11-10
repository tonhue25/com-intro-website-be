package com.altek.intro.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altek.intro.dto.RecruitmentDTO;
import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.RecruitmentService;

@RestController
@RequestMapping("/recruitment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecruitmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentController.class);

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping
    public ResponseEntity<RecruitmentDTO> listAll() {
        try {
            List<RecruitmentDTO> response = recruitmentService.getAllRecruitment();
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<RecruitmentDTO> list(@RequestBody ListRequestDto requestDto) {
        try {
            ListResponseDto<RecruitmentDTO> response = recruitmentService.getList(requestDto);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
