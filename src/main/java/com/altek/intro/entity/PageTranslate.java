package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PAGE_TRANSLATE")
public class PageTranslate extends AbstractEntity{
    @Column(name = "PAGE_TITLE")
    private String pageTitle;

    @Column(name = "SHORT_DESCRIPTION", length = 1000)
    private String shortDescription;

    @Column(name = "LANGUAGE_ID")
    private String languageId;

    @Column(name = "PAGE_ID")
    private Long pageId;
}
