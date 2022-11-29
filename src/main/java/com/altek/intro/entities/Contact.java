package com.altek.intro.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_CONTACT")
public class Contact extends AbstractEntity implements Serializable {

    @Column(name = "FULL_NAME")
    private String fullName;
    private String phoneNumber;
    @Column(name = "PHONE_NUMBER", unique = true, length = 12)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String email;
    @Column(name = "EMAIL", unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "MESSAGE")
    private String message;
}
