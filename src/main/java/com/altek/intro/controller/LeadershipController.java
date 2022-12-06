package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.LeadershipResponseDto;
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
    public ResponseEntity<LeadershipResponseDto> getListLeadership(@RequestBody BaseRequest request) {
        try {
            return new ResponseEntity(leadershipService.getListLeadership(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
