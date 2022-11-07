package com.altek.intro.model;

import lombok.Data;

@Data
public class LeadershipModel extends AbstractModel {
    private String image;
    private String name;
    private String position;
    private String information;
}
