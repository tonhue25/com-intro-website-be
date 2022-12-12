package com.altek.intro.entity;

import com.altek.intro.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_CONTACT")
public class Contact extends AbstractEntity {

    @Column(name = "FULL_NAME")
    private String fullName;
    private String phoneNumber;
    private String email;
    @Column(name = "MESSAGE")
    private String message;
    private ContactType type;

    @Column(name = "PHONE_NUMBER", length = 12)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public ContactType getType() {
        return type;
    }
}
