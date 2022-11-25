package com.altek.intro.repository;

import com.altek.intro.entities.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends AbstractRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM ALT_CANDIDATE WHERE STATUS = 1", nativeQuery = true)
    List<Candidate> findAll();

    @Query("select c from Candidate c where c.status = 1 and c.phoneNumber = :phoneNumber")
    Optional<Candidate> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
