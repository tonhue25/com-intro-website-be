package com.altek.intro.repository;

import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.entites.PageDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageDetailRepository extends AbstractRepository<PageDetailEntity, Long>{
    Optional<PageDetailEntity> findByPageContentId(Long id);
}
