package com.altek.intro.repository;

import java.util.Optional;

import com.altek.intro.entities.Page;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.PageDetail;

@Repository
public interface PageDetailRepository extends AbstractRepository<PageDetail, Long>{
    Optional<PageDetail> findByPageContent(Page pageContent);

    boolean existsByPageContent(Page pageContent);
}
