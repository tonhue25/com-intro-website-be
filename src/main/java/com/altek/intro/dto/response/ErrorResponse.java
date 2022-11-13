package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
    private String url;

    public ErrorResponse(HttpStatus status, HttpServletRequest request, Exception ex) {
        this.error = status.toString();
        this.message = ex.getMessage();
        this.url = request.getRequestURL().toString();
    }
}
