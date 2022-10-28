package com.altek.intro.dto;

import com.altek.intro.entites.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;

@Data
public class PageContentDTO extends AbstractDTO {
    private String pageTitle;
    private String shortDescription;
    private String type;
    private String menuCode;
}
