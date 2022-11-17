package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// client gửi thông tin về server.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipRequestDto {
     String image;
     String name;
     String position;
     String information;

     private String facebook;

     private String linkedin;
}
