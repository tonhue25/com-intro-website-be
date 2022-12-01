package com.altek.intro.entity;

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
public class Page extends AbstractEntity {

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "TIME_LINE")
    private String timeline;

    @Column(name = "URL")
    private String url;

    private PageDetail pageDetail;
    @OneToOne(mappedBy = "page")
    public PageDetail getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(PageDetail pageDetail) {
        this.pageDetail = pageDetail;
    }

    private Menu menu;
    @ManyToOne()
    public Menu getMenu() {
        return menu;
    }

    @Column(name = "ICON")
    private String icon;
}
