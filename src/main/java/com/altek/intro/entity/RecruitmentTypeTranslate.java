package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_TYPE_TRANSLATE")
public class RecruitmentTypeTranslate extends AbstractEntity implements Serializable {
    private String name;
    @Column(name = "NAME", unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "LANGUAGE_ID")
    private String languageId;

    private RecruitmentType recruitmentType;
    @ManyToOne
    public RecruitmentType getRecruitmentType() {
        return recruitmentType;
    }
}
