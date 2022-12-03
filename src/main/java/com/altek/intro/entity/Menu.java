package com.altek.intro.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_MENU")
public class Menu extends AbstractEntity {

    private List<Page> pageContents;
    @OneToMany(mappedBy = "menu")
    public List<Page> getPageContents() {
        return pageContents;
    }
    private Menu parentId;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Menu getParentId() {
        return parentId;
    }
}