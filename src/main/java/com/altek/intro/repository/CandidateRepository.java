package com.altek.intro.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.altek.intro.entity.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends AbstractRepository<Candidate, Long> {
    @Query("select c from Candidate c where c.status = 1 and c.id = :id")
    Optional<Candidate> findById(Long id);
    @Query("select c from Candidate c where c.status = 1 and c.phoneNumber = :phoneNumber")
    Optional<Candidate> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query(value = "SELECT * FROM ALT_CANDIDATE WHERE STATUS = 1", nativeQuery = true)
    List<Candidate> findAll();
}
