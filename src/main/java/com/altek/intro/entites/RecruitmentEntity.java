package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALT_RECRUITMENT")
public class RecruitmentEntity extends AbstractEntity implements Serializable {

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FILE")
    private String file;

    @Column(name = "LOCATION")
    private String location;

}
