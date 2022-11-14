package com.altek.intro.repository;

import java.util.Optional;

import com.altek.intro.entities.PageContentEntity;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.PageDetailEntity;

@Repository
public interface PageDetailRepository extends AbstractRepository<PageDetailEntity, Long>{
    Optional<PageDetailEntity> findByPageContent(PageContentEntity pageContent);

    boolean existsByPageContent(PageContentEntity pageContent);
}
