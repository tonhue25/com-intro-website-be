package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipResponseDto extends AbstractResponseDto {
     String image;
     String name;
     String position;
     String information;
}
