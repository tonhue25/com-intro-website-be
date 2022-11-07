package com.altek.intro.model;

import lombok.Data;

@Data
public class RecruitmentModel extends AbstractModel {
    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
