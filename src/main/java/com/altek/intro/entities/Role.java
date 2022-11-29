package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_ROLE")
public class Role extends AbstractEntity implements Serializable {

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    List<UserRole> userRoles;
    @OneToMany(mappedBy = "role")
    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
