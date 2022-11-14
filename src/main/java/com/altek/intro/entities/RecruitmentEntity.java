package com.altek.intro.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT")
public class RecruitmentEntity extends AbstractEntity implements Serializable {

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

//    private List<CandidateEntity> candidates;
//    @OneToMany(mappedBy = "recruitment")
//    public List<CandidateEntity> getCandidates() {
//        return candidates;
//    }

    private List<CandidateEntity> candidates;
    @ManyToMany(mappedBy = "recruitments")
    public List<CandidateEntity> getCandidates() {
        return candidates;
    }
}
