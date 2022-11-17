package com.altek.intro.controller;

import com.altek.intro.dto.request.LoginRequestDto;
import com.altek.intro.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto request) {
        return ResponseEntity.ok(loginService.login(request));
    }

}
