package com.altek.intro.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CV")
    private String cv;

    private List<Recruitment> recruitments;
    @ManyToMany
    @JoinTable(name = "RECRUITMENT_CANDIDATE",
            joinColumns = @JoinColumn(name = "CANDIDATE_ID"),
            inverseJoinColumns = @JoinColumn(name = "RECRUITMENT_ID")
    )
    public List<Recruitment> getRecruitments() {
        return recruitments;
    }

}
