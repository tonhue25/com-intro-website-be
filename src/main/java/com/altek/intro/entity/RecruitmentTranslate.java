package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_TRANSLATE")
public class RecruitmentTranslate extends AbstractEntity {
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "LANGUAGE_ID")
    private String languageId;
    @Column(name = "REQUIREMENT")
    private String requirement;
    private Recruitment recruitment;

    @ManyToOne
    public Recruitment getRecruitment() {
        return recruitment;
    }
}
