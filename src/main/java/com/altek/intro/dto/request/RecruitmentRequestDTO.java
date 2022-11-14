package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class RecruitmentRequestDTO {
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
