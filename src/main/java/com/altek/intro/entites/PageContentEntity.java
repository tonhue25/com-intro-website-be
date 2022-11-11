package com.altek.intro.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PAGE_CONTENT")
public class PageContentEntity extends AbstractEntity implements Serializable {

    @Column(name = "PAGE_TITLE")
    private String pageTitle;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "TIMELINE_HISTORY")
    private String timeLine;

    @Column(name = "EVENT_HISTORY")
    private String eventName;

    @Column(name = "MENU_ID")
    private Long menuId;
}
