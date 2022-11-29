package com.altek.intro.entity;

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
    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FILE_LINK")
    private String fileLink;

<<<<<<< HEAD:src/main/java/com/altek/intro/entities/Recruitment.java
    @Column(name = "LOCATION")
    private String location;

=======
>>>>>>> tonhue:src/main/java/com/altek/intro/entity/Recruitment.java
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
