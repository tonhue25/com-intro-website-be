package com.altek.intro.repository;

import com.altek.intro.entity.Leadership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadershipRepository extends AbstractRepository<Leadership, Long> {
    @Query(value = "select u from Leadership u where u.status = 1 and u.id = :id")
    Optional<Leadership> findById(Long id);

    @Query(value = "SELECT * FROM ALT_LEADERSHIP WHERE STATUS = 1", nativeQuery = true)
    List<Leadership> findAll();

    Leadership findLeadershipById(Long id);
}
