package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class CandidateRequestDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
