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
public class NewsEntity extends AbstractEntity implements Serializable {

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    private NewsDetailEntity newsDetail;
    @OneToOne(mappedBy = "news")
    public NewsDetailEntity getNewsDetail() {
        return newsDetail;
    }
}