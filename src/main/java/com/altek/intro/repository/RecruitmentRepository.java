package com.altek.intro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altek.intro.entites.RecruitmentEntity;

@Repository
public interface RecruitmentRepository extends  AbstractRepository<RecruitmentEntity, Long>{
    
    @Query(value = "SELECT * FROM ALT_RECRUITMENT where STATUS = 1 ", nativeQuery = true)
    List<RecruitmentEntity> findAll();

    @Query(value = "select * from ALT_RECRUITMENT where STATUS = 1 and LOWER(JOB_TITLE) like %:jobTitle%" , nativeQuery = true)
    Page<RecruitmentEntity> getList(@Param("jobTitle") String jobTitle, Pageable pageable);
}
