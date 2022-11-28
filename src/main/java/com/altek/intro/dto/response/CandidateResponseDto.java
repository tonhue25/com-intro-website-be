package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDto extends AbstractResponseDto {

    private Long id;
    private Integer status;
    private String fullName;
    private String email;
    private String phoneNumber;
    private byte[] cv;
    private String gender;
    private String address;
    private String dateOfBirth;
    private Long recruitmentId;
    private String address;
    private String dateOfBirth;
    private String gender;
}
