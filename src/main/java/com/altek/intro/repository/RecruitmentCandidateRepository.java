package com.altek.intro.repository;

import com.altek.intro.entities.Candidate;
import com.altek.intro.entities.Recruitment;
import com.altek.intro.entities.RecruitmentCandidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentCandidateRepository extends AbstractRepository<RecruitmentCandidate, Long> {
    @Query(value = "select u from RecruitmentCandidate u where u.status = 1 and u.recruitment = :recruitment and u.candidate = :candidate ")
    Optional<RecruitmentCandidate> findByRecruitmentAndCandidate(@Param("recruitment") Recruitment recruitment,@Param("candidate") Candidate candidate);

    @Query(value = "select u from RecruitmentCandidate u where u.status = 1 and u.recruitment = :recruitment")
    List<RecruitmentCandidate> findByRecruitment(@Param("recruitment") Recruitment recruitment);
}
