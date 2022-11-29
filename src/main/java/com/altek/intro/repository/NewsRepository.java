package com.altek.intro.repository;

import com.altek.intro.entities.News;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NewsRepository extends AbstractRepository<News, Long> {
    @Query(value = "select u from News u where u.status = 1 and u.id = :id")
    Optional<News> findById(Long id);
<<<<<<< HEAD

    @Query("select e from News e where  e.status = 1 and (:search is null or " +
            "   ( lower(e.title) like lower(concat(:search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat(:search, '%')) )) ")
    List<News> getAll(@Param("search") String search);

=======
>>>>>>> tonhue
}
