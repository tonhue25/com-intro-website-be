package com.altek.intro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.LeadershipEntity;

@Repository
public interface LeadershipRepository extends  AbstractRepository<LeadershipEntity, Long>{
    @Query(value = "SELECT * FROM ALT_LEADERSHIP where STATUS = 1 ", nativeQuery = true)
    List<LeadershipEntity> findAll();

}
