package com.altek.intro.dto;

import lombok.Data;

@Data
public class ContactDTO{

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String message;
    private Integer status;

}
