package com.altek.intro.services;

import com.altek.intro.dto.request.UserRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface UserService {
    BaseResponse createUser(UserRequestDto request);

    BaseResponse deleteUser(Long id);

    BaseResponse getUser(String username);
}
