package com.altek.intro.repository;

import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.dto.response.MenuResponseDto;
import com.altek.intro.entity.LeadershipTranslate;
import com.altek.intro.entity.MenuTranslate;
import com.altek.intro.entity.NewsTranslate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTranslateRepository extends AbstractRepository<MenuTranslate, Long> {
    @Query(value = "select mt.* from ALT_MENU_TRANSLATE mt, ALT_MENU m where language_id = :language and mt.menu_id = m.id " +
            " and m.status = 1", nativeQuery = true)
    List<MenuTranslate> get(@Param("language") String language);
    @Query(value = "select mt.* from ALT_MENU_TRANSLATE mt, ALT_MENU m where language_id = :language and mt.menu_id = m.id " +
            " and m.parent_id = :parentId", nativeQuery = true)
    List<MenuTranslate> getNav(@Param("language") String language, @Param("parentId") Long parentId);

    @Query(value = "select mt from MenuTranslate mt " +
            "where mt.status = 1 and mt.menu.id = :menuId and mt.languageId = :languageId")
    MenuTranslate findMenuTranslateByMenuIdAndLanguageId(@Param("menuId") Long menuId,
                                            @Param("languageId") String languageId);

    @Query(value = "SELECT lt FROM NewsTranslate lt WHERE lt.status = 1 AND lt.news.id = :menuId")
    List<MenuTranslate> findMenuTranslateByMenuId(@Param("menuId") Long menuId);
    @Query(value = "SELECT new com.altek.intro.dto.response.MenuResponseDto(m.id, m.status, m.createdBy, " +
            "TO_CHAR(m.createdTime, 'dd/MM/yyyy'), m.lastUpdatedBy,TO_CHAR(m.lastUpdatedTime, 'dd/MM/yyyy'), mt.label, " +
            "mt.link, mt.languageId, mt.menu.id) from MenuTranslate mt inner join Menu m on mt.menu.id = m.id " +
            "where mt.menu.id = :newsId and mt.languageId = :languageId")
    MenuResponseDto findByMenuIdAndLanguageId(@Param("newsId") Long newsId, @Param("languageId") String languageId);
}
