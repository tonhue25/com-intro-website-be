package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDto extends AbstractResponseDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String cv;
    private Long recruitmentId;
}
