package com.altek.intro.dto;

import lombok.Data;

@Data
public class DetailContentDTO extends AbstractDTO{
    private String title;
    private String shortDescription;
    private  String content;
    private  String thumbnail;
    private  String image;
    private  String comLink;
    private Long contentId;
    private String type;
}
