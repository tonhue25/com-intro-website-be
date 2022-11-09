package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALT_NEWS")
public class NewsEntity extends AbstractEntity implements Serializable {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

}