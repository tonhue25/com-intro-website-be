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
@Table(name = "ALT_USER")
public class User extends AbstractEntity implements Serializable {
    private String username;
    private String password;
    private String email;

//    private List<Role> roles;
//    @ManyToMany
//    @JoinTable(	name = "ALT_USER_ROLE",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//    public List<Role> getRoles() {
//        return roles;
//    }

    List<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
