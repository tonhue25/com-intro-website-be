package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PAGE_TRANSLATE")
public class PageTranslate extends AbstractEntity {
    @Column(name = "PAGE_TITLE")
    private String pageTitle;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "LANGUAGE_ID")
    private String languageId;

    @Column(name = "DETAIL", length = 4000)
    private String detail;
    private Page page;

    @ManyToOne
    public Page getPage() {
        return page;
    }
}
