package com.altek.intro.entity;

import java.util.List;

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
public class News extends AbstractEntity {
    @Column(name = "THUMBNAIL")
    private String thumbnail;

    private List<NewsTranslate> newsTranslate;
    @OneToMany(mappedBy = "news")
    public List<NewsTranslate> getNewsTranslate() {
        return newsTranslate;
    }

}