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

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;

    @Column(name = "GENDER")
    private String gender;


    private List<Recruitment_Candidate> candidateRecruitments;
    @OneToMany(mappedBy = "candidate")
    public List<Recruitment_Candidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
