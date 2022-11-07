package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ALT_NEWS")
public class NewsEntity extends AbstractEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

}