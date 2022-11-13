package com.altek.intro.utils;

import org.springframework.stereotype.Component;

import com.altek.intro.dto.response.BaseResponse;

@Component
public class ResponseUtil {

    public BaseResponse responseBean(String errorCode, String message) {
        return new BaseResponse(errorCode, message);
    }

    public BaseResponse responseBean(String http_code, String message, Object content) {
        return new BaseResponse(http_code, message, content);
    }

    public BaseResponse responseBean(String http_code,  Object content) {
        return new BaseResponse(http_code, content);
    }

    public BaseResponse responseBean(String http_code, String message, String content) {
        return new BaseResponse(http_code, message, content);
    }
}
