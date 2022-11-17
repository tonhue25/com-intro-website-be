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
@Table(name = "ALT_CANDIDATE")
public class Candidate extends AbstractEntity implements Serializable {

    @Column(name = "FULL_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CV")
    private String cv;

//    private List<Recruitment> recruitments;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "RECRUITMENT_CANDIDATE",
//            joinColumns = @JoinColumn(name = "CANDIDATE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "RECRUITMENT_ID")
//    )
//    public List<Recruitment> getRecruitments() {
//        return recruitments;
//    }
    private List<Candidate_Recruitment> candidateRecruitments;
    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    public List<Candidate_Recruitment> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
