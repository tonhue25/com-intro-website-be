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

    @Column(name = "LINK")
    private String link;
}