package com.altek.intro.repository;

import com.altek.intro.entites.DetailContentEntity;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends AbstractRepository<DetailContentEntity, Long>{
    @Query(value = "SELECT * FROM ALT_DETAIL_CONTENT where ACTIVE_FLAG = 1", nativeQuery = true)
    List<DetailContentEntity> findAll();
    List<DetailContentEntity> findAllByContentId(Long contentId);

}

