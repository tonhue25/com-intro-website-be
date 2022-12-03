package com.altek.intro.service;

import com.altek.intro.dto.request.LoginRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.JwtResponse;

public interface LoginService {
    BaseResponse login(LoginRequestDto loginRequest);
}
