package com.altek.intro.dto;

import lombok.Data;

@Data
public class PageContentDTO extends AbstractDTO{

    private String pageTitle;
    private String shortDescription;
    private String menuCode;
    private String address;
    private String phoneNumber;
    private String image;
    private String timeLine;
    private String eventName;
    private Long menuId;

}
