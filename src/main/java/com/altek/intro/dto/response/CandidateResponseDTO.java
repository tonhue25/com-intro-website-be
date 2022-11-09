package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// return client
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
