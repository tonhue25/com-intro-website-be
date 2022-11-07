package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ALT_LEADERSHIP")
public class LeadershipEntity extends AbstractEntity{

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "NAME")
    private String name;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "INFORMATION")
    private String information;

}
