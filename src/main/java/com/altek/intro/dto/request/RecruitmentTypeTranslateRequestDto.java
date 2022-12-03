package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentTypeTranslateRequestDto {

    private Long id;
    private Integer status;
    private String name;
    private String languageId;
    private Long recruitmentTypeId;

}
