package com.altek.intro.repository;

import com.altek.intro.entities.Menu;
import com.altek.intro.entities.Page;
import com.altek.intro.entities.PageTranslate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageTranslateRepository extends AbstractRepository<PageTranslate, Long>{
    @Query("SELECT pt FROM PageTranslate  pt INNER JOIN Page p ON pt.pageId = p.id " +
            "WHERE p.status = 1 AND pt.languageId = :language")
    List<PageTranslate> findAllPageContent(@Param("language") String language);

    @Query("SELECT pt, p.image FROM PageTranslate  pt INNER JOIN Page p ON pt.pageId = p.id " +
            "WHERE p.status = 1 AND pt.languageId = :language AND p.menu.id = :menuId")
    List<PageTranslate> findAllPageContentByMenuID(@Param("language") String language, @Param("menuId") Long menuId);

}
