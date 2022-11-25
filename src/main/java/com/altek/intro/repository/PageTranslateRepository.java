package com.altek.intro.repository;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.PageResponseDto;
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

    @Query("SELECT new com.altek.intro.dto.response.PageResponseDto (p.id, p.status, p.createdBy, " +
            "CHAR_TO(p.createdTime,'dd/MM/yyyyy'), p.lastUpdatedBy, CHAR_TO(p.lastUpdatedTime,'dd/MM/yyyyy') , " +
            "pt.pageTitle, pt.shortDescription, p.image, p.menu.id, CHAR_TO(p.timeLine, 'dd/MM/yyyy'), p.url) " +
            "FROM PageTranslate  pt INNER JOIN Page p ON pt.pageId = p.id " +
            "WHERE p.status = 1 AND pt.languageId = :language AND p.menu.id = :menuId")
    List<PageResponseDto> findAllPageContentByMenuID(@Param("language") String language,
                                                    @Param("menuId") Long menuId, Pageable pageable);

//    List<Page> findByMenuAndStatus(Menu menu, Integer status);
//
//    @Query(value = "select u from Page u where u.status = 1 and u.id = :id")
//    Optional<Page> findById(Long id);
//
//    @Query("select e from Page e where  e.status = 1 and (e.menu = :menu) and" +
//            " ( lower(e.pageTitle) like lower(concat('%', :search, '%')) )")
//    org.springframework.data.domain.Page<Page> getList(@Param("search")String search, @Param("menu") Menu menu, Pageable pageable);
}
