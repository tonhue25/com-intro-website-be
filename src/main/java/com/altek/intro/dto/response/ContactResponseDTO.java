package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDTO extends AbstractResponseDTO{

    private String name;
    private String phoneNumber;
    private String email;
    private String message;

}
