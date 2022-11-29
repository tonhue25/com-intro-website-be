package com.altek.intro.repository;

import com.altek.intro.entity.Role;
import com.altek.intro.entity.User;
import com.altek.intro.entity.UserRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends AbstractRepository<UserRole, Long>{

    @Query(value = "select u from UserRole u where u.status = 1 and u.role = :role and u.user = :user")
    Optional<UserRole> findByRoleAndUser(Role role, User user);

    @Query(value = "select u from UserRole u where u.status = 1 and u.user = :user")
    List<UserRole> findByUser(User user);
}
