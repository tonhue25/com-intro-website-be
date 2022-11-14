package com.altek.intro.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "ALT_DETAIL_NEWS")
public class NewsDetailEntity extends AbstractEntity implements Serializable {
    @Column(name = "CONTENT", length = 1000)
    private String content;

    private NewsEntity news;
    @OneToOne
    @JoinColumn(name = "NEWS_ID", nullable = false)
    public NewsEntity getNews() {
        return news;
    }
}