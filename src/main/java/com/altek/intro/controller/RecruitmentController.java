package com.altek.intro.controller;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.RecruitmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruitment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecruitmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentController.class);

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping
    public ResponseEntity<RecruitmentResponseDTO> listAll() {
        try {
            List<RecruitmentResponseDTO> response = recruitmentService.getAllRecruitment();
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
    public ResponseEntity<RecruitmentResponseDTO> list(@RequestBody ListRequestDto requestDto) {
        try {
            ListResponseDto<RecruitmentResponseDTO> response = recruitmentService.getList(requestDto);
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
