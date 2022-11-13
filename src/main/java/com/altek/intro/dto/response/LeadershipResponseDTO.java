package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipResponseDTO extends AbstractResponseDTO{
     String image;
     String name;
     String position;
     String information;
}
