package com.altek.intro.repository;

import com.altek.intro.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends AbstractRepository<News, Long> {
    @Query("select e from News e where  e.status = 1 and " +
            "   ( lower(e.title) like lower(concat(:search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat(:search, '%')) )")
    Page<News> getList(@Param("search") String search,
                       Pageable pageable);

    @Query(value = "select u from News u where u.status = 1 and u.id = :id")
    Optional<News> findById(Long id);

    @Query("select e from News e where  e.status = 1 and (:search is null or " +
            "   ( lower(e.title) like lower(concat(:search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat(:search, '%')) )) ")
    List<News> getAll(@Param("search") String search);

}
