package com.altek.intro.dto;

import lombok.Data;

@Data
public class PageContentViewDTO {
    private Long id;
    private String pageTitle;
    private String shortDescription;
    private String type;
    private String menuCode;
}
