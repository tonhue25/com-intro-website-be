package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {
    private String http_code;
    private String message;
    private Object data;

    public BaseResponse(String http_code) {
        this.http_code = http_code;
    }

    public BaseResponse(String http_code, String message) {
        this.http_code = http_code;
        this.message = message;
    }

    public BaseResponse(String http_code, Object data) {
        this.http_code = http_code;
        this.data = data;
    }

}
