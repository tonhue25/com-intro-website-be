package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_MENU_TRANSLATE")
public class MenuTranslate extends AbstractEntity {

    @Column(name = "LABEL")
    private String label;

    @Column(name = "LINK")
    private String link;

    @Column(name = "LANGUAGE_ID")
    private String languageId;
    private Menu menu;

    @ManyToOne
    public Menu getMenu() {
        return menu;
    }
}