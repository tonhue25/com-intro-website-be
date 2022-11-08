package com.altek.intro.repository;

import com.altek.intro.entites.LeadershipEntity;
import com.altek.intro.entites.RecruitmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadershipRepository extends  AbstractRepository<LeadershipEntity, Long>{
    @Query(value = "SELECT * FROM ALT_LEADERSHIP where STATUS = 1 ", nativeQuery = true)
    List<LeadershipEntity> findAll();

}
