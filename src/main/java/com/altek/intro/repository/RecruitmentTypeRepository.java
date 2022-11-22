package com.altek.intro.repository;

import com.altek.intro.entities.RecruitmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentTypeRepository extends AbstractRepository<RecruitmentType, Long> {

    @Query(value = "SELECT * FROM ALT_RECRUITMENT_TYPE where STATUS = 1 ", nativeQuery = true)
    List<RecruitmentType> findAll();

}

