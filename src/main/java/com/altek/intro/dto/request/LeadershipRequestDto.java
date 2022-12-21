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
    String fullName;
    String position;
    String information;
    String facebook;
    String linkedIn;
    String languageId;
    String team;
    Long leadershipId;
}
