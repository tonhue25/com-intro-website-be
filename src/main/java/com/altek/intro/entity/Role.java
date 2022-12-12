package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_ROLE")
public class Role extends AbstractEntity {
    private String name;
    private List<UserRole> userRoles;

    @Column(name = "NAME", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "role")
    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
