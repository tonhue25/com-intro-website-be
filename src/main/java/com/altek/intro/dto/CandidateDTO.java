package com.altek.intro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// return client
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO extends AbstractDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
