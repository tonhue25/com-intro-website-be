package com.altek.intro.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_NEWS_DETAIL")
public class NewsDetail extends AbstractEntity implements Serializable {
    @Column(name = "CONTENT", length = 1000)
    private String content;

    private News news;
    @OneToOne
    @JoinColumn(name = "NEWS_ID", nullable = false, unique = true)
    public News getNews() {
        return news;
    }
}