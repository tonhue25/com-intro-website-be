package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class PageRequestDto extends BaseRequest {
    private String pageTitle;
    private String shortDescription;
    private String address;
    private String phoneNumber;
    private String image;
    private String timeLine;
    private String eventName;
    private Long menuId;
    private String url;
}
