package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class ContactRequestDTO {

    private String name;
    private String phoneNumber;
    private String email;
    private String message;

}
