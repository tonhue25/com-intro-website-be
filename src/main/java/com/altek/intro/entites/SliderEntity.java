package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ALT_SLIDER")
public class SliderEntity extends AbstractEntity{

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CONTENT")
    private String content;
}
