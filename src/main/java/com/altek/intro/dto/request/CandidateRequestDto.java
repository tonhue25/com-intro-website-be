package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class CandidateRequestDto {
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
}

