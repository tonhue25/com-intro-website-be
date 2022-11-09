package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class PageDetailRequestDTO {

    private String title;
    private String shortDescription;
    private  String content;
    private  String thumbnail;
    private  String image;
    private Long pageContentId;

}
