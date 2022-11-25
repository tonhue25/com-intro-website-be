package com.altek.intro.service;

import com.altek.intro.dto.request.LoginRequestDto;
import com.altek.intro.dto.response.JwtResponse;

public interface LoginService {
    JwtResponse login(LoginRequestDto loginRequest);
}