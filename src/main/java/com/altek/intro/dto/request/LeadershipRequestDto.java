package com.altek.intro.dto.request;

import com.altek.intro.enums.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// client gửi thông tin về server.
@Data
@AllArgsConstructor
@NoArgsConstructor
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
