package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
