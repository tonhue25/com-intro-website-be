package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALT_SLIDER")
public class SliderEntity extends AbstractEntity implements Serializable {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CONTENT")
    private String content;
}
