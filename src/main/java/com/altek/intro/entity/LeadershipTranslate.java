package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_LEADERSHIP_TRANSLATE")
public class LeadershipTranslate extends AbstractEntity{

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "INFORMATION")
    private String information;

    @Column(name = "LANGUAGE_ID")
    private String languageId;

    @Column(name = "LEADERSHIP_ID")
    private Long leadershipId;
}
