package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentTypeResponseDto extends  AbstractResponseDto{
    private String name;
    private String languageId;
    private Long recruitmentTypeId;

    public RecruitmentTypeResponseDto(Long id, Integer status, String createdBy, String createdTime,
                                      String lastUpdatedBy, String lastUpdatedTime, String name,
                                      String languageId, Long recruitmentTypeId) {
        super(id, status, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);
        this.name = name;
        this.languageId = languageId;
        this.recruitmentTypeId = recruitmentTypeId;
    }
}
