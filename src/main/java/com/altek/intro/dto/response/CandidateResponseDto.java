package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDto extends AbstractResponseDto {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
    private String address;
    private String dateOfBirth;
    private String gender;
}
