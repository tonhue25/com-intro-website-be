package com.altek.intro.entities;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_CANDIDATE")
public class Recruitment_Candidate extends AbstractEntity implements Serializable {
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
