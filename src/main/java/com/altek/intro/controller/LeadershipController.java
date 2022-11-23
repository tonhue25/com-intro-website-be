package com.altek.intro.controller;

import java.util.List;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.LeadershipService;
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

    @GetMapping
    public ResponseEntity<LeadershipResponseDto> listAll(@RequestHeader("Accept-Language") String lang) {
        try {
            BaseResponse response = leadershipService.getAllLeadership(lang);
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
    public ResponseEntity<LeadershipResponseDto> create(@RequestBody LeadershipRequestDto request){
        try {
            LeadershipResponseDto result = leadershipService.create(request);
            return new ResponseEntity<LeadershipResponseDto>(result,HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeadershipResponseDto> delete(@PathVariable("id") Long id){
        LeadershipResponseDto result = leadershipService.delete(id);
        return new ResponseEntity<LeadershipResponseDto>(result, HttpStatus.OK);
    }
}
