package com.altek.intro.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class AbstractEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="STATUS")
    private int status;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_TIME")
    private Date createdTime;

    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name="LAST_UPDATED_TIME")
    private Date lastUpdatedTime;

    @Column(name="DESCRIPTION")
    private String description;

}
