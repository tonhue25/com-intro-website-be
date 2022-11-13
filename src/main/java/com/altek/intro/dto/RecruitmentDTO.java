package com.altek.intro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentDTO extends AbstractDTO {
    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
