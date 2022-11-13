package com.altek.intro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderShipDTO extends AbstractDTO {
    String image;
    String name;
    String position;
    String information;
    private String lastUpdatedBy;
}
