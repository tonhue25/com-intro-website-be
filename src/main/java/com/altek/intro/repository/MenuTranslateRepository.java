package com.altek.intro.repository;

import com.altek.intro.entities.Menu;
import com.altek.intro.entities.MenuTranslate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuTranslateRepository extends AbstractRepository<MenuTranslate, Long> {
    @Query(value = "select mt.* from ALT_MENU_TRANSLATE mt, ALT_MENU m where language_id = :language and mt.menu_id = m.id " +
            " and m.status = 1", nativeQuery = true)
    List<MenuTranslate> get(@Param("language") String language);
    @Query(value = "select mt.* from ALT_MENU_TRANSLATE mt, ALT_MENU m where language_id = :language and mt.menu_id = m.id " +
            " and m.parent_id = :parentId", nativeQuery = true)
    List<MenuTranslate> getNav(@Param("language") String language, @Param("parentId") Long parentId);
}
