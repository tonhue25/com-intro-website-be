package com.altek.intro.controller;

import com.altek.intro.dto.request.LoginRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequestDto request) {
        try {
            return ResponseEntity.ok(loginService.login(request));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
