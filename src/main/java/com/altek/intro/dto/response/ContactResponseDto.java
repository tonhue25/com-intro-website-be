package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto extends AbstractResponseDto {

    private String name;
    private String phoneNumber;
    private String email;
    private String message;

}
