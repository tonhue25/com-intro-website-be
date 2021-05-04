package com.altek.intro.repository;

import com.altek.intro.entites.ClientReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientReviewRepository extends AbstractRepository<ClientReviewEntity, Long> {
    @Query(value = "SELECT * FROM ALT_CLIENT_REVIEW where ACTIVE_FLAG = 1 ", nativeQuery = true)
    List<ClientReviewEntity> findAll();
}
