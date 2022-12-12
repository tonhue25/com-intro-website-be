package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String message;
    private Integer status;
    private String type;

    @NotNull(message = "fullName is mandatory")
    public String getFullName() {
        return fullName;
    }

    @NotNull(message = "phoneNumber is mandatory, unique")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Email(message = "invalid email")
    public String getEmail() {
        return email;
    }
}

