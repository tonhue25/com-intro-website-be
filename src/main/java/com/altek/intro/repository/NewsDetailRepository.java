package com.altek.intro.repository;

import com.altek.intro.entity.NewsDetail;
import com.altek.intro.entity.News;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NewsDetailRepository extends AbstractRepository<NewsDetail, Long>{

    @Query(value = "select u from NewsDetail u where u.status = 1 and u.news = :news")
    Optional<NewsDetail> findByNews(News news);

    @Query(value = "select u from NewsDetail u where u.status = 1 and u.id = :id")
    Optional<NewsDetail> findById(Long id);
}
