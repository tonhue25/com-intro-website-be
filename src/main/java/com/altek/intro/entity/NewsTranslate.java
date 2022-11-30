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
@Table(name = "ALT_NEWS_TRANSLATE")
public class NewsTranslate extends AbstractEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "LANGUAGE_ID")
    private String languageId;

    private News news;
    @ManyToOne
    public News getNews() {
        return news;
    }
    @Column(name = "DETAIL", length = 1000)
    private String detail;

    private String thumbnail;
    @Transient
    public String getThumbnail() {
        return thumbnail;
    }

}
