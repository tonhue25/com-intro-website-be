package com.altek.intro.repository;

import com.altek.intro.entity.RecruitmentTypeTranslate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface RecruitmentTypeTranslateRepository extends AbstractRepository<RecruitmentTypeTranslate, Long> {

    @Query(value = "select p from RecruitmentTypeTranslate p where p.status = 1 and p.id = :id")
    Optional<RecruitmentTypeTranslate> findById(@RequestParam("id") Long id);

}
