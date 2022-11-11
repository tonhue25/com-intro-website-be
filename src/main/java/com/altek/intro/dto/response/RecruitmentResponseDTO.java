package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentResponseDTO extends AbstractResponseDTO{
    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
