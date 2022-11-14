package com.altek.intro.repository;

import com.altek.intro.entities.NewsDetail;
import com.altek.intro.entities.News;

import java.util.Optional;

public interface NewsDetailRepository extends AbstractRepository<NewsDetail, Long>{
    Optional<NewsDetail> findByNews(News newsEntity);
}
