package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ALT_NEWS_TRANSLATE")
public class NewsTranslate extends AbstractEntity implements Serializable {

    @Column(name = "LANGUAGE_ID")
    private Long languageId;

    @Column(name = "CONTENT")
    private String content;

    private News news;

    @ManyToOne
    @JoinColumn(name = "news_id")
    public News getNews() {
        return news;
    }
}
