package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PAGE")
public class Page extends AbstractEntity {

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "TIME_LINE")
    private String timeline;

    @Column(name = "URL")
    private String url;
    private Menu menu;
    @Column(name = "ICON")
    private String icon;
    private List<PageTranslate> pageTranslates;

    @OneToMany(mappedBy = "page")
    public List<PageTranslate> getPageTranslates() {
        return pageTranslates;
    }

    @ManyToOne()
    public Menu getMenu() {
        return menu;
    }
}
