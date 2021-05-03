package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ALT_COMPANY_INFO")
public class CompanyInfoEntity extends AbstractEntity{
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;
    @Column(name = "CONTENT")
    private  String content;
    @Column(name = "IMAGE")
    private  String image;
    @Column(name = "PHONE_NUMBER")
    private  String phoneNumber;
}
