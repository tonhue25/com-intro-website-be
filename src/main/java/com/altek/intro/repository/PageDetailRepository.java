package com.altek.intro.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.altek.intro.entites.PageDetailEntity;

@Repository
public interface PageDetailRepository extends AbstractRepository<PageDetailEntity, Long>{
    Optional<PageDetailEntity> findByPageContentId(Long id);
}
