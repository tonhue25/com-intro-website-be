package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentResponseDto extends AbstractResponseDto {

    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
