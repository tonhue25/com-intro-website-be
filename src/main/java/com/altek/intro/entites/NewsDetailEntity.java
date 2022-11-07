package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ALT_DETAIL_NEWS")
public class NewsDetailEntity extends AbstractEntity {

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NEWS_ID")
    private Long newsId;
}