package com.altek.intro.repository;

import com.altek.intro.entities.NewsDetailEntity;
import com.altek.intro.entities.NewsEntity;

import java.util.Optional;

public interface NewsDetailRepository extends AbstractRepository<NewsDetailEntity, Long>{
    Optional<NewsDetailEntity> findByNews(NewsEntity newsEntity);
}
