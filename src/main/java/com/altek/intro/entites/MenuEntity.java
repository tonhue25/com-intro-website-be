package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ALT_MENU")
public class MenuEntity extends AbstractEntity {

    @Column(name = "LABEL")
    private String label;

    @Column(name = "MENU_TO")
    private String menuTo;

    @Column(name = "EXACT")
    private Long exact;

    @Column(name = "CODE")
    private String code;

}