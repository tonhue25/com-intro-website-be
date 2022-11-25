package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_CANDIDATE")
public class RecruitmentCandidate extends AbstractEntity implements Serializable {
    Recruitment recruitment;
    @ManyToOne
    @JoinColumn(name = "recruitment_id",nullable = false)
    public Recruitment getRecruitment() {
        return recruitment;
    }

    Candidate candidate;
    @ManyToOne
    @JoinColumn(name = "candidate_id",nullable = false)
    public Candidate getCandidate() {
        return candidate;
    }

    public RecruitmentCandidate(Integer status, Recruitment recruitment, Candidate candidate) {
        super(status);
        this.recruitment = recruitment;
        this.candidate = candidate;
    }
}
