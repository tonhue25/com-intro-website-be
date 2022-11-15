package com.altek.intro.repository;

import com.altek.intro.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Long>{
    @Query(value = "select u from User u where u.status = 1 and u.username = :username")
    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.status = 1 and u.id = :id")
    Optional<User> findById(Long id);
}
