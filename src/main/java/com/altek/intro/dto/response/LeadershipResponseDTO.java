package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// server gửi thông tin về client.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipResponseDTO {
     String image;
     String name;
     String position;
     String information;
     private String lastUpdatedBy;
}
