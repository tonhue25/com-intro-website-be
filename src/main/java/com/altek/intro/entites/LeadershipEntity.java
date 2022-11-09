package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALT_LEADERSHIP")
public class LeadershipEntity extends AbstractEntity implements Serializable {

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FULL_NAME")
    private String name;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "INFORMATION")
    private String information;

}
