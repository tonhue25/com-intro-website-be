package com.altek.intro.controller;

import java.util.List;

import com.altek.intro.dto.request.LeadershipRequestDTO;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.LeadershipService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.altek.intro.dto.response.LeadershipResponseDTO;


@RestController
@RequestMapping("/leadership")
@Slf4j
public class LeadershipController {

    @Autowired
    private LeadershipService leadershipService;

    @GetMapping
    public ResponseEntity<LeadershipResponseDTO> listAll() {
        try {
            List<LeadershipResponseDTO> response = leadershipService.getAllLeadership();
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
    public ResponseEntity<LeadershipResponseDTO> Create(@RequestBody LeadershipRequestDTO request){
        try {
            LeadershipResponseDTO result = leadershipService.Create(request);
            return new ResponseEntity<LeadershipResponseDTO>(result,HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeadershipResponseDTO> delete(@PathVariable("id") Long id){
        LeadershipResponseDTO result = leadershipService.Delete(id);
        return new ResponseEntity<LeadershipResponseDTO>(result, HttpStatus.OK);
    }

}
