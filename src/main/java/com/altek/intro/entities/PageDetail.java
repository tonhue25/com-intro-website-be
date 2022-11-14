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
@Table(name = "ALT_DETAIL_CONTENT")
public class PageDetail extends AbstractEntity implements Serializable {

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

    private Page pageContent;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGE_CONTENT_ID", nullable = false)
    public Page getPageContent() {
        return pageContent;
    }

    public void setPageContent(Page pageContent) {
        this.pageContent = pageContent;
    }
}
