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
    private String fileLink;
    private String location;
    private Long recruitmentTypeId;
    private String languageId;
    private Long recruitmentId;

    private Long productGroupId;

    public RecruitmentResponseDto(Long id, Integer status, String createdBy, String createdTime,
                                  String lastUpdatedBy, String lastUpdatedTime, String jobTitle, String jobDescription,
                                  String image, String fileLink, String location, Long recruitmentTypeId, String languageId,
                                  Long recruitmentId , Long productGroupId) {
        super(id, status, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.image = image;
        this.fileLink = fileLink;
        this.location = location;
        this.recruitmentTypeId = recruitmentTypeId;
        this.languageId = languageId;
        this.recruitmentId = recruitmentId;
        this.productGroupId = productGroupId;
    }
}
