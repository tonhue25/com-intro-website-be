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
@Table(name = "ALT_PAGE_DETAIL")
public class PageDetail extends AbstractEntity{

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @Column(name = "IMAGE")
    private String image;

    private Page page;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGE_ID", nullable = false, unique = true)
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
