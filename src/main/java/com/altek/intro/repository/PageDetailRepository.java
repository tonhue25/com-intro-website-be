package com.altek.intro.repository;

import java.util.Optional;

import com.altek.intro.entities.News;
import com.altek.intro.entities.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.PageDetail;

@Repository
public interface PageDetailRepository extends AbstractRepository<PageDetail, Long>{

    @Query(value = "select u from PageDetail u where u.status = 1 and u.page = :page")
    Optional<PageDetail> findByPage(Page page);

    @Query(value = "select u from PageDetail u where u.status = 1 and u.page = :page")
    boolean existsByPage(Page page);

    @Query(value = "select u from PageDetail u where u.status = 1 and u.id = :id")
    Optional<PageDetail> findById(Long id);
}
