package com.altek.intro.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.*;

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

    private List<RecruitmentCandidate> recruitmentCandidate;
    @OneToMany(mappedBy = "candidate")
    public List<RecruitmentCandidate> getRecruitmentCandidate() {
        return recruitmentCandidate;
    }
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
