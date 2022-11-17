package com.altek.intro.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CANDIDATE_RECRUITMENT")
public class Candidate_Recruitment extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;

}
