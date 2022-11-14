package com.altek.intro.repository;

import com.altek.intro.entities.User;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
