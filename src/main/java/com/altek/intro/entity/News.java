package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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