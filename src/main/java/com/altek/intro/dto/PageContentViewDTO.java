package com.altek.intro.dto;

import lombok.Data;

@Data
public class PageContentViewDTO {
    private Long id;
    private String pageTitle;
    private String shortDescription;
    private Long menuId;
    private String type;
}
