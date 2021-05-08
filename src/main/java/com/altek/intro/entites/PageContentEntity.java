package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ALT_PAGE_CONTENT")
public class PageContentEntity extends AbstractEntity{

    @Column(name = "PAGE_TITLE")
    private String pageTitle;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "MENU_CODE")
    private String menuCode;
}
