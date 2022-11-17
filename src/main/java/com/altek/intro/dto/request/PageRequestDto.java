package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class PageRequestDto {
    private Long id;
    private String pageTitle;
    private String shortDescription;
    private String address;
    private String phoneNumber;
    private String image;
    private String timeLine;
    private String eventName;
    private Long menuId;
    private Integer status;
    private String url;
}
