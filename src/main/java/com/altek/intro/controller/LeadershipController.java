package com.altek.intro.controller;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.exception.AuthorizedException;
import com.altek.intro.exception.ParameterIllegalException;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.exception.SystemErrorException;
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
    public ResponseEntity<LeadershipResponseDto> getLeaderships(@RequestBody BaseRequest request) {
        try {
            return new ResponseEntity(leadershipService.getLeaderships(request), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createNewLeadership(@RequestBody LeadershipRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#LeadershipController.createNewLeadership: START -- ");
            response = leadershipService.createNewLeadership(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParameterIllegalException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.createNewLeadership ParameterIllegalException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.createNewLeadership AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.createNewLeadership SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateLeadership(@RequestBody LeadershipRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#LeadershipController.updateLeadership: START -- ");
            response = leadershipService.updateLeadership(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParameterIllegalException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.updateLeadership ParameterIllegalException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.updateLeadership AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.updateLeadership SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLeadership(@RequestBody LeadershipRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#LeadershipController.deleteLeadership: START -- ");
            response = leadershipService.deleteLeadership(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.deleteLeadership ResourceNotFoundException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.NOT_FOUND.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.deleteLeadership AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#LeadershipController.deleteLeadership SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
