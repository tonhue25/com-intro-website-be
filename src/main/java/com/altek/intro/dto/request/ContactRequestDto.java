package com.altek.intro.dto.request;

import lombok.*;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {

    private Long id;
    private String fullName;
    @NotNull(message = "fullName is mandatory")
    public String getFullName() {
        return fullName;
    }
    private String phoneNumber;
    @NotNull(message = "phoneNumber is mandatory, unique")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    private String email;
    @Email(message = "invalid email")
    public String getEmail() {
        return email;
    }
    private String message;
    private Integer status;
    private String type;
}

