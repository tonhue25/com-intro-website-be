package com.altek.intro.repository;

import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.RecruitmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends  AbstractRepository<RecruitmentEntity, Long>{
    @Query(value = "SELECT * FROM ALT_RECRUITMENT where STATUS = 1 ", nativeQuery = true)
    List<RecruitmentEntity> findAll();

}
