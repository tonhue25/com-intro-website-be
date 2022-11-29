package com.altek.intro.repository;

import com.altek.intro.entities.Leadership;
import com.altek.intro.entities.LeadershipTranslate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadershipTranslateRepository extends  AbstractRepository<LeadershipTranslate, Long>{
    @Query(value = "SELECT lt, l.facebook, l.linkedIn, l.image FROM LeadershipTranslate lt, Leadership l WHERE lt.leadershipId = l.id and " +
            "l.status = 1 and lt.languageId = :language")
    List<LeadershipTranslate> findAll(@Param("language") String language);

    @Query(value = "select u from Leadership u where u.status = 1 and u.id = :id")
    Optional<LeadershipTranslate> findById(Long id);
}
