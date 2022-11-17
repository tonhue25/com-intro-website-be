package com.altek.intro.repository;

import java.util.List;
import java.util.Optional;

import com.altek.intro.entities.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.Leadership;

@Repository
public interface LeadershipRepository extends  AbstractRepository<Leadership, Long>{
    @Query(value = "SELECT * FROM ALT_LEADERSHIP where STATUS = 1 ", nativeQuery = true)
    List<Leadership> findAll();

    @Query(value = "select u from Leadership u where u.status = 1 and u.id = :id")
    Optional<Leadership> findById(Long id);
}
