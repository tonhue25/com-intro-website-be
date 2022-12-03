package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_ROLE")
public class Role extends AbstractEntity{

    private String name;
    @Column(name = "NAME", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    List<UserRole> userRoles;
    @OneToMany(mappedBy = "role")
    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
