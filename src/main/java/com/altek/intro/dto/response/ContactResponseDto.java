package com.altek.intro.dto.response;

import com.altek.intro.enums.ContactType;
import com.altek.intro.repository.ContactRepository;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto extends AbstractResponseDto {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String message;
    private ContactType type;

}
