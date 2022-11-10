package com.altek.intro.dto.request;

import lombok.Data;

// return client
@Data
public class CandidateRequestDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
