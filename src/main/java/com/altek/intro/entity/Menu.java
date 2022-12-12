package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_MENU")
public class Menu extends AbstractEntity {
    private List<Page> pageContents;
    private Menu parentId;

    @OneToMany(mappedBy = "menu")
    public List<Page> getPageContents() {
        return pageContents;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Menu getParentId() {
        return parentId;
    }
}