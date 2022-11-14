package com.altek.intro.entities;

import java.io.Serializable;
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
@Table(name = "ALT_MENU")
public class Menu extends AbstractEntity implements Serializable {

    @Column(name = "LABEL")
    private String label;

    @Column(name = "LINK")
    private String link;

    private List<Page> pageContents;
    @OneToMany(mappedBy = "menu")
    public List<Page> getPageContents() {
        return pageContents;
    }
}