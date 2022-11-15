package com.altek.intro.repository;

import com.altek.intro.entities.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends AbstractRepository<Role, Long>{

    @Query(value = "select u from Role u where u.status = 1 and u.id = :id")
    Optional<Role> findById(Long id);

}
