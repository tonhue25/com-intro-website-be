package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT")
public class Recruitment extends AbstractEntity implements Serializable {
    List<ProductgroupRecruitment> productgroupRecruitments;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "FILE_LINK")
    private String fileLink;
    private RecruitmentType recruitmentType;
    private List<RecruitmentCandidate> candidateRecruitments;
    private List<RecruitmentTranslate> recruitmentTranslates;

    @ManyToOne
    public RecruitmentType getRecruitmentType() {
        return recruitmentType;
    }

    @OneToMany(mappedBy = "recruitment")
    public List<ProductgroupRecruitment> getProductgroupRecruitments() {
        return productgroupRecruitments;
    }

    @OneToMany(mappedBy = "recruitment")
    public List<RecruitmentCandidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }

    @OneToMany(mappedBy = "recruitment")
    public List<RecruitmentTranslate> getRecruitmentTranslates() {
        return recruitmentTranslates;
    }
}
