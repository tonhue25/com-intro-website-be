package com.altek.intro.model;

import lombok.Data;

@Data
public class ContactModel extends AbstractModel {

    private String name;
    private String phoneNumber;
    private String email;
    private String message;

}
