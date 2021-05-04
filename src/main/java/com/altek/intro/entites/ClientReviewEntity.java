package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ALT_CLIENT_REVIEW")
public class ClientReviewEntity extends AbstractEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "EVALUATE")
    private Long evaluate;
    @Column(name = "IMAGE")
    private String image;

}
