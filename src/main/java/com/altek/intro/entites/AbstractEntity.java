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
    @Column(name="ACTIVE_FLAG")
    private int activeFlag;
    @Column(name="CREATE_BY")
    private String createBy;
    @Column(name="CREATE_DATE")
    private Date createDate;
    @Column(name="MODIFY_BY")
    private String modifyBy;
    @Column(name="MODIFY_DATE")
    private Date modifyDate;
}
