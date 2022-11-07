package com.altek.intro.model;

import lombok.Data;

@Data
public class PageContentModel extends AbstractModel {

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
