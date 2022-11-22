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
@Table(name = "ALT_NEWS")
public class News extends AbstractEntity implements Serializable {

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    private NewsDetail newsDetail;
    @OneToOne(mappedBy = "news")
    public NewsDetail getNewsDetail() {
        return newsDetail;
    }
}