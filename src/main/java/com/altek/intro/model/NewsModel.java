package com.altek.intro.model;

import lombok.Data;

@Data
public class NewsModel extends AbstractModel {

    private String title;
    private String thumbnail;
    private String shortDescription;

}
