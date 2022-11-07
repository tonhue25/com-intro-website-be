package com.altek.intro.model;

import lombok.Data;

@Data
public class MenuModel extends AbstractModel {

    private String label;
    private String code;
    private String link;
}
