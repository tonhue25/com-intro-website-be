package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.service.LeadershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderships")
@Slf4j
public class LeadershipController {

    @Autowired
    private LeadershipService leadershipService;

    @PostMapping("list")
    public ResponseEntity<LeadershipResponseDto> listAll(@RequestBody BaseRequest request) {
        return new ResponseEntity(leadershipService.getListLeadership(request), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LeadershipResponseDto> create(@RequestBody LeadershipRequestDto request) {
        try {
            LeadershipResponseDto result = leadershipService.create(request);
            return new ResponseEntity<LeadershipResponseDto>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeadershipResponseDto> delete(@PathVariable("id") Long id) {
        LeadershipResponseDto result = leadershipService.delete(id);
        return new ResponseEntity<LeadershipResponseDto>(result, HttpStatus.OK);
    }
}
