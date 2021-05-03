package com.altek.intro.repository;

import com.altek.intro.entites.PageContentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageContentRepository extends AbstractRepository<PageContentEntity, Long>{
    @Query(value = "SELECT * FROM ALT_PAGE_CONTENT where ACTIVE_FLAG = 1", nativeQuery = true)
    List<PageContentEntity> findAll();
    List<PageContentEntity> findAllByMenuId(Long menuId);

}
