package com.altek.intro.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PAGE")
public class Page extends AbstractEntity{

    @Column(name = "PAGE_TITLE")
    private String pageTitle;

    @Column(name = "SHORT_DESCRIPTION", length = 1000)
    private String shortDescription;

    @Column(name = "IMAGE")
    private String image;

    private PageDetail pageDetail;
    @OneToOne(mappedBy = "page")
    public PageDetail getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(PageDetail pageDetail) {
        this.pageDetail = pageDetail;
    }

    private Menu menu;
    @ManyToOne(fetch = FetchType.LAZY)
    public Menu getMenu() {
        return menu;
    }

    @Column(name = "ICON")
    private String icon;
}
