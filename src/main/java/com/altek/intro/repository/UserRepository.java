package com.altek.intro.repository;

import com.altek.intro.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends AbstractRepository<User, Long>{
    @Query(value = "select u from User u where u.status = 1 and u.username = :username")
    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.status = 1 and u.id = :id")
    Optional<User> findById(Long id);
}
