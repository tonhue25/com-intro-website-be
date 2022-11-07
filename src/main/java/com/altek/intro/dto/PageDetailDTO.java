package com.altek.intro.dto;

import lombok.Data;

@Data
public class PageDetailDTO{

    private String title;
    private String shortDescription;
    private  String content;
    private  String thumbnail;
    private  String image;
    private Long pageContentId;

}