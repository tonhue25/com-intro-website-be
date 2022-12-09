package com.altek.intro.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_MENU")
public class Menu extends AbstractEntity {

    //    private List<Page> pageContents;
//    @OneToMany(mappedBy = "menu")
//    public List<Page> getPageContents() {
//        return pageContents;
//    }

    private Long parentId;
    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}