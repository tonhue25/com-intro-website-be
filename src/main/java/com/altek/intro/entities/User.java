package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

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

    List<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
