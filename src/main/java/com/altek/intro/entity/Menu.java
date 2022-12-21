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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_MENU")
public class Menu extends AbstractEntity {
    private List<Page> pageContents;
    //    private Menu parentId;
    private Long parentId;

//    @ManyToOne
//    @JoinColumn(name = "PARENT_ID")
//    public Menu getParentId() {
    //    private List<Page> pageContents;
//    @OneToMany(mappedBy = "menu")
//    public List<Page> getPageContents() {
//        return pageContents;
//    }

    @OneToMany(mappedBy = "menu")
    public List<Page> getPageContents() {
        return pageContents;
    }

    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }
}