package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadershipRequestDto {
    Long id;
    String image;
    String name;
    String position;
    String information;

    private String facebook;

    private String linkedin;
}
