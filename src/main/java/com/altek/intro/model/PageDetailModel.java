package com.altek.intro.model;

import lombok.Data;

@Data
public class PageDetailModel extends AbstractModel {

    private String title;
    private String shortDescription;
    private  String content;
    private  String thumbnail;
    private  String image;
    private Long pageContentId;
    
}
