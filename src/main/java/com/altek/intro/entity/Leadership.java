package com.altek.intro.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.altek.intro.enums.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_LEADERSHIP")
public class Leadership extends AbstractEntity implements Serializable {

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FACEBOOK")
    private String facebook;

    @Column(name = "LINKED_IN")
    private String linkedIn;

    private EmployeeType team;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public EmployeeType getTeam() {
        return team;
    }
}
