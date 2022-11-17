package com.altek.intro.controller;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruitments")
@Slf4j
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping
    public ResponseEntity<RecruitmentResponseDto> listAll() {
        try {
            List<RecruitmentResponseDto> response = recruitmentService.getAllRecruitment();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> list(@RequestBody ListRequestDto requestDto) {
        return new ResponseEntity(recruitmentService.getList(requestDto), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<RecruitmentResponseDTO> create(@RequestBody RecruitmentRequestDTO request){
//        try {
//            RecruitmentResponseDTO result = recruitmentService.create(request);
//            return new ResponseEntity<RecruitmentResponseDTO>(result,HttpStatus.CREATED);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecruitmentResponseDto> delete(@PathVariable("id") Long id){
        RecruitmentResponseDto result = recruitmentService.delete(id);
        return new ResponseEntity<RecruitmentResponseDto>(result, HttpStatus.OK);
    }
}
