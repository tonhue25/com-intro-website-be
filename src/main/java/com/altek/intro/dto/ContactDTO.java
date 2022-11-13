package com.altek.intro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO extends AbstractDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String message;

}
