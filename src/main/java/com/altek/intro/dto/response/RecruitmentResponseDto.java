package com.altek.intro.dto.response;

import com.altek.intro.entities.ProductgroupRecruitment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentResponseDto extends AbstractResponseDto {

    private Long recruitmentId;
    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;
    private String location;
    List<ProductgroupRecruitmentResponseDto> productgroupRecruitments;
}
