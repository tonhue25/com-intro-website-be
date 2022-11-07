package com.altek.intro.dto;

import lombok.Data;

@Data
public class ContactDTO extends AbstractDTO{

    private String name;
    private String phoneNumber;
    private String email;
    private String message;

}
