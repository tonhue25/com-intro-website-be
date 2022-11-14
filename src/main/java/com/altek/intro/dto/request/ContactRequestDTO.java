package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class ContactRequestDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String message;
    private Integer status;
}
