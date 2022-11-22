package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_RECRUITMENT_TYPE")
public class RecruitmentType extends AbstractEntity implements Serializable {
    private String name;
    @Column(name = "NAME", unique = true)
    public String getName() {
        return name;
    }

    private List<Recruitment> recruitment;
    @OneToMany(mappedBy = "recruitmentType")
    public List<Recruitment> getRecruitment() {
        return recruitment;
    }
}
