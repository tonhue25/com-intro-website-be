package com.altek.intro.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_CANDIDATE")
public class Recruitment_Candidate extends AbstractEntity implements Serializable {

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
}
