package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class CandidateRequestDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
