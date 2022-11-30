package com.altek.intro.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_TRANSLATE")
public class RecruitmentTranslate extends AbstractEntity implements Serializable {
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "LANGUAGE_ID")
    private String languageId;
    private Recruitment recruitment;
    @ManyToOne
    public Recruitment getRecruitment() {
        return recruitment;
    }
}
