package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALT_MENU")
public class MenuEntity extends AbstractEntity implements Serializable {

    @Column(name = "LABEL")
    private String label;

    @Column(name = "LINK")
    private String link;
}