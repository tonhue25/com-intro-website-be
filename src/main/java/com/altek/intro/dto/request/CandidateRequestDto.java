package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequestDto{
    private Long id;
    private Integer status;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private String address;
    private String dateOfBirth;
    private String gender;

}
