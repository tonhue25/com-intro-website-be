package com.altek.intro.repository;

import com.altek.intro.dto.response.PageResponseDto;
import com.altek.intro.entity.PageTranslate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageTranslateRepository extends AbstractRepository<PageTranslate, Long> {
    @Query("SELECT pt FROM PageTranslate  pt INNER JOIN Page p ON pt.page.id = p.id " +
            "WHERE p.status = 1 AND pt.languageId = :language")
    List<PageTranslate> findAllPageContent(@Param("language") String language);

    @Query("SELECT new com.altek.intro.dto.response.PageResponseDto (p.id, p.status, p.createdBy, " +
            "TO_CHAR(p.createdTime,'dd/MM/yyyy'), p.lastUpdatedBy, TO_CHAR(p.lastUpdatedTime,'dd/MM/yyyy'), " +
            "pt.pageTitle, pt.shortDescription, p.image, p.timeline, p.menu.id, p.url) " +
            "FROM PageTranslate  pt INNER JOIN Page p ON pt.page.id = p.id " +
            "WHERE p.status = 1 AND pt.languageId = :language AND p.menu.id = :menuId")
    List<PageResponseDto> findAllPageContentByMenuID(@Param("language") String language,
                                                     @Param("menuId") Long menuId, Pageable pageable);

    @Query("SELECT count(pt) FROM PageTranslate pt INNER JOIN Page p ON pt.page.id = p.id " +
            "WHERE p.status = 1 AND pt.languageId = :language AND p.menu.id = :menuId")
    Long countAllPageContentByMenuID(@Param("language") String language,
                                     @Param("menuId") Long menuId);


}
