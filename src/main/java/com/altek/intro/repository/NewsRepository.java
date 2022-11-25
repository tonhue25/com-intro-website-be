package com.altek.intro.repository;

import com.altek.intro.entity.News;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NewsRepository extends AbstractRepository<News, Long> {
    @Query(value = "select u from News u where u.status = 1 and u.id = :id")
    Optional<News> findById(Long id);
}
