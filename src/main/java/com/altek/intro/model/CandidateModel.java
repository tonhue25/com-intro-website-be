package com.altek.intro.model;

import lombok.Data;

@Data
public class CandidateModel extends AbstractModel {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
