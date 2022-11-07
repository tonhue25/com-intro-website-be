package com.altek.intro.dto;

import lombok.Data;

@Data
public class LeadershipDTO extends AbstractDTO{
    private String image;
    private String name;
    private String position;
    private String information;
}
