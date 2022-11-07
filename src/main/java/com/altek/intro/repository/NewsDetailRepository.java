package com.altek.intro.repository;

import com.altek.intro.entites.NewsDetailEntity;

import java.util.Optional;

public interface NewsDetailRepository extends AbstractRepository<NewsDetailEntity, Long>{
    Optional<NewsDetailEntity> findByNewsId(long id);
}
