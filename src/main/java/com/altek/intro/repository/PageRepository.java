package com.altek.intro.repository;

import com.altek.intro.entities.Menu;
import com.altek.intro.entities.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends AbstractRepository<Page, Long>{
    @Query(value = "SELECT * FROM ALT_PAGE_CONTENT where STATUS = 1", nativeQuery = true)
    List<Page> findAll();

    List<Page> findByMenuAndStatus(Menu menu, Integer status);

    @Query("select e from Page e where  e.status = 1 and (e.menu = :menu) and" +
            " ( lower(e.pageTitle) like lower(concat('%', :search, '%')) )")
    org.springframework.data.domain.Page<Page> getList(@Param("search")String search, @Param("menu") Menu menu, Pageable pageable);
}
