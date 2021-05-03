package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ALT_DETAIL_CONTENT")
public class DetailContentEntity extends AbstractEntity{
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;
    @Column(name = "CONTENT")
    private  String content;
    @Column(name = "THUMBNAIL")
    private  String thumbnail;
    @Column(name = "IMAGE")
    private  String image;
    @Column(name = "COM_LINK")
    private  String comLink;
    @Column(name = "CONTENT_ID")
    private Long contentId;
    @Column(name = "TYPE")
    private String type;
}

