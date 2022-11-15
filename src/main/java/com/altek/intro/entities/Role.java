package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String name;

//    private List<User> users;
//    @ManyToMany(mappedBy = "roles")
//    public List<User> getUsers() {
//        return users;
//    }
    List<UserRole> userRoles;
    @OneToMany(mappedBy = "role")
    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
