package com.altek.intro.repository;

import com.altek.intro.entities.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandiDateRepository extends AbstractRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM ALT_CANDIDATE WHERE STATUS = 1", nativeQuery = true)
    List<Candidate> findAll();

}
