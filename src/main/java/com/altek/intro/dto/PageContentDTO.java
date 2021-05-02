package com.altek.intro.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PageContentDTO extends AbstractDTO{
    private String pageTitle;
    private String shortDescription;
    private int menuId;
    private String type;
}
