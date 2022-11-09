package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALT_DETAIL_NEWS")
public class NewsDetailEntity extends AbstractEntity implements Serializable {

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NEWS_ID")
    private Long newsId;
}