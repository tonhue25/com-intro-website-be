package com.altek.intro.dto;

import lombok.Data;

@Data
public class RecruitmentDTO extends AbstractDTO{

    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
