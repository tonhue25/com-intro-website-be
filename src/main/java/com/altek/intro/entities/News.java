package com.altek.intro.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class News extends AbstractEntity implements Serializable {

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    private List<NewsTranslate> newsTranslate;
    @OneToMany(mappedBy = "news")
    public List<NewsTranslate> getNewsTranslate() {
        return newsTranslate;
    }
}