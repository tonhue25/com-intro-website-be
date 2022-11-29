package com.altek.intro.entities;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_CANDIDATE")
public class RecruitmentCandidate extends AbstractEntity implements Serializable {

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "BENEFIT")
    private String benefit;

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
