package com.altek.intro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.RecruitmentEntity;

@Repository
public interface RecruitmentRepository extends AbstractRepository<RecruitmentEntity, Long> {

    @Query(value = "SELECT * FROM ALT_RECRUITMENT where STATUS = 1 ", nativeQuery = true)
    List<RecruitmentEntity> findAll();

    @Query("select e from RecruitmentEntity e where  e.status = 1 and (:search is null or " +
            "  lower(e.jobTitle) like  lower( concat('%',:search, '%')))")
    Page<RecruitmentEntity> getList(@Param("search") String search,
                                    Pageable pageable);
}
