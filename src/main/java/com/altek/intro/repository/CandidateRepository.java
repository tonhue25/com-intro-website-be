package com.altek.intro.repository;

<<<<<<< HEAD
import com.altek.intro.entities.Candidate;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
=======
import com.altek.intro.entities.Recruitment;
=======
import com.altek.intro.entity.Candidate;
>>>>>>> tonhue
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
>>>>>>> tonhue
=======
>>>>>>> tonhue

@Repository
public interface CandidateRepository extends AbstractRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM ALT_CANDIDATE WHERE STATUS = 1", nativeQuery = true)
    List<Candidate> findAll();

<<<<<<< HEAD
<<<<<<< HEAD
=======
    @Query("select c from Candidate c where c.status = 1 and c.phoneNumber = :phoneNumber")
    Optional<Candidate> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
>>>>>>> tonhue
=======
    @Query("select c from Candidate c where c.status = 1 and c.phoneNumber = :phoneNumber")
    Optional<Candidate> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
>>>>>>> tonhue
}
