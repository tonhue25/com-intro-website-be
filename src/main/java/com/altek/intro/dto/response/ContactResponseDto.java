package com.altek.intro.dto.response;

import com.altek.intro.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDto extends AbstractResponseDto {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String message;
    private ContactType type;

}
