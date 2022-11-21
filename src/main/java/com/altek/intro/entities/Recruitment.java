package com.altek.intro.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

//    private List<Candidate> candidates;
//    @ManyToMany(mappedBy = "recruitments")
//    public List<Candidate> getCandidates() {
//        return candidates;
//    }
    private List<Recruitment_Candidate> candidateRecruitments;

    @OneToMany(mappedBy = "recruitment")
    public List<Recruitment_Candidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
