package com.altek.intro.dto.response;

import lombok.*;

// return client
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDTO extends AbstractResponseDTO{

    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
