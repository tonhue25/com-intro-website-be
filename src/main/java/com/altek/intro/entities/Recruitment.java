package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT")
public class Recruitment extends AbstractEntity implements Serializable {

    @Column(name = "JOB_TITLE", nullable = false)
    private String jobTitle;

    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FILE")
    private String file;

    @Column(name = "LOCATION")
    private String location;

    private RecruitmentType recruitmentType;
    @ManyToOne(fetch = FetchType.LAZY)
    public RecruitmentType getRecruitmentType() {
        return recruitmentType;
    }

    List<ProductgroupRecruitment> productgroupRecruitments;
    @OneToMany(mappedBy = "recruitment")
    public List<ProductgroupRecruitment> getProductgroupRecruitments() {
        return productgroupRecruitments;
    }

    private List<Recruitment_Candidate> candidateRecruitments;

    @OneToMany(mappedBy = "recruitment")
    public List<Recruitment_Candidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
