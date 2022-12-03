package com.altek.intro.entity;

import lombok.*;

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
    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FILE_LINK")
    private String fileLink;

    private RecruitmentType recruitmentType;
    @ManyToOne
    public RecruitmentType getRecruitmentType() {
        return recruitmentType;
    }

    List<ProductgroupRecruitment> productgroupRecruitments;
    @OneToMany(mappedBy = "recruitment")
    public List<ProductgroupRecruitment> getProductgroupRecruitments() {
        return productgroupRecruitments;
    }

    private List<RecruitmentCandidate> candidateRecruitments;
    @OneToMany(mappedBy = "recruitment")
    public List<RecruitmentCandidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
