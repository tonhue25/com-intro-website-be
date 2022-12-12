package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_CANDIDATE")
public class Candidate extends AbstractEntity implements Serializable {

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;
    private String phoneNumber;
    private byte[] cv;
    private List<RecruitmentCandidate> recruitmentCandidate;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "ADDRESS")
    private String address;
    private Date dateOfBirth;
    private List<RecruitmentCandidate> candidateRecruitments;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Lob
    @Column(name = "CV", columnDefinition = "BLOB")
    public byte[] getCv() {
        return cv;
    }

    @OneToMany(mappedBy = "candidate")
    public List<RecruitmentCandidate> getRecruitmentCandidate() {
        return recruitmentCandidate;
    }

    @Column(name = "DATE_OF_BIRTH", columnDefinition = "date")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @OneToMany(mappedBy = "candidate")
    public List<RecruitmentCandidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
