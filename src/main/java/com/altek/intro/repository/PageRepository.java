package com.altek.intro.repository;

import com.altek.intro.entity.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends AbstractRepository<Page, Long>{
    @Query(value = "SELECT * FROM ALT_PAGE_CONTENT where STATUS = 1", nativeQuery = true)
    List<Page> findAll();

    @Query(value = "select u from Page u where u.status = 1 and u.id = :id")
    Optional<Page> findById(Long id);

}
