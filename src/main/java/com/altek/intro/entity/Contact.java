package com.altek.intro.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.altek.intro.enums.ContactType;
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
public class Contact extends AbstractEntity{

    @Column(name = "FULL_NAME")
    private String fullName;
    private String phoneNumber;
    @Column(name = "PHONE_NUMBER", length = 12)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String email;
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @Column(name = "MESSAGE")
    private String message;

    private ContactType type;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public ContactType getType() {
        return type;
    }
}
