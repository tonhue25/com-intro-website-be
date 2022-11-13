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
@Table(name = "ALT_NEWS")
public class NewsEntity extends AbstractEntity implements Serializable {

    @Column(name = "TITLE")
    private String title;
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

}