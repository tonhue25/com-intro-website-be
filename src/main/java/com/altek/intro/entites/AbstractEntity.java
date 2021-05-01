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
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="active_flag")
    private int activeFlag;
    @Column(name="create_date")
    private Date createDate;
    @Column(name="modify_by")
    private String modifyBy;
    @Column(name="modify_date")
    private Date modifyDate;
}
