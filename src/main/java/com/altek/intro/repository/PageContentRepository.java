package com.altek.intro.repository;

import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageContentRepository extends AbstractRepository<PageContentEntity, Long>{
    @Query(value = "SELECT * FROM ALT_PAGE_CONTENT where STATUS = 1", nativeQuery = true)
    List<PageContentEntity> findAll();

    List<PageContentEntity> findByMenuAndStatus(MenuEntity menu, Integer status);

    @Query("select e from PageContentEntity e where  e.status = 1 and (e.menu = :menu) and" +
            " ( lower(e.pageTitle) like lower(concat('%', :search, '%')) )")
    Page<PageContentEntity> getList(@Param("search")String search,@Param("menu") MenuEntity menu, Pageable pageable);
}
