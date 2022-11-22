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
@AllArgsConstructor
@Entity
@Table(name = "ALT_MENU")
public class Menu extends AbstractEntity {

//    private List<Page> pageContents;
//    @OneToMany(mappedBy = "menu")
//    public List<Page> getPageContents() {
//        return pageContents;
//    }

}