package com.altek.intro.dto;

import lombok.Data;

@Data
public class CandidateDTO extends AbstractDTO{
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
