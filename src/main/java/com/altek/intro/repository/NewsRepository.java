package com.altek.intro.repository;

import com.altek.intro.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends AbstractRepository<News, Long> {
    @Query(value = "select u from News u where u.status = 1 and u.id = :id")
    Optional<News> findById(Long id);

    @Query("SELECT n FROM News n WHERE n.status = 1 AND n.id = :id")
    News findNewsById(@Param("id") Long id);
}
