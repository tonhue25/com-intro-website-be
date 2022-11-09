package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentResponseDTO {
    private String jobTitle;
    private String jobDescription;
    private String image;
    private String file;

}
