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
    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    private byte[] cv;
    @Lob
    @Column(name = "CV", columnDefinition = "BLOB")
    public byte[] getCv() {
        return cv;
    }

<<<<<<< HEAD:src/main/java/com/altek/intro/entities/Candidate.java
    private List<RecruitmentCandidate> recruitmentCandidate;
    @OneToMany(mappedBy = "candidate")
    public List<RecruitmentCandidate> getRecruitmentCandidate() {
        return recruitmentCandidate;
    }
=======
>>>>>>> tonhue:src/main/java/com/altek/intro/entity/Candidate.java
    @Column(name = "GENDER")
    private String gender;

    @Column(name = "ADDRESS")
    private String address;

    private Date dateOfBirth;
    @Column(name = "DATE_OF_BIRTH", columnDefinition = "date")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    private List<RecruitmentCandidate> candidateRecruitments;
    @OneToMany(mappedBy = "candidate")
    public List<RecruitmentCandidate> getCandidateRecruitments() {
        return candidateRecruitments;
    }
}
