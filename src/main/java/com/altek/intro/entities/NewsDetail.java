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
@Table(name = "ALT_NEWS_DETAIL")
public class NewsDetail extends AbstractEntity implements Serializable {
    @Column(name = "CONTENT", length = 1000)
    private String content;

    private News news;
    @OneToOne
    @JoinColumn(name = "NEWS_ID", nullable = false)
    public News getNews() {
        return news;
    }
}