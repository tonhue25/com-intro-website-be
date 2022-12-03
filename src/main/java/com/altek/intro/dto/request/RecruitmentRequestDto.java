package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentRequestDto {
    private Long id;
    private Integer status;
    private String image;
    private String fileLink;
    private Long recruitmentTypeId;

    private String jobTitle;
    private String jobDescription;
    private String location;
    private String languageId;
    private String requirement;
    private Long recruitmentId;
}
