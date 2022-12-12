package com.altek.intro.dto.response;

import com.altek.intro.enums.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadershipResponseDto extends AbstractResponseDto {
    String image;
    String fullName;
    String position;
    String information;
    String facebook;
    String linkedIn;
    String languageId;
    Long leadershipId;

    EmployeeType team;

    public LeadershipResponseDto(Long id, Integer status, String createdBy, String createdTime, String lastUpdatedBy,
                                 String lastUpdatedTime, String image, String fullName, String position, String information,
                                 String facebook, String linkedIn, String languageId, Long leadershipId, EmployeeType team) {
        super(id, status, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);
        this.image = image;
        this.fullName = fullName;
        this.position = position;
        this.information = information;
        this.facebook = facebook;
        this.linkedIn = linkedIn;
        this.languageId = languageId;
        this.leadershipId = leadershipId;
        this.team = team;
    }
}
