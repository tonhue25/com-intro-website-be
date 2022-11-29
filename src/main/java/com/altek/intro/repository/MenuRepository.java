package com.altek.intro.repository;

import com.altek.intro.dto.response.MenuResponseDto;
import com.altek.intro.entities.Menu;
import com.altek.intro.entities.MenuTranslate;
import com.altek.intro.entities.Recruitment;
import com.altek.intro.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface MenuRepository extends  AbstractRepository<Menu, Long>{
    @Query(value = "SELECT * FROM ALT_MENU WHERE STATUS = 1 ", nativeQuery = true)
=======
public interface MenuRepository extends AbstractRepository<Menu, Long> {
    @Query(value = "SELECT * FROM ALT_MENU where STATUS = 1 ", nativeQuery = true)
>>>>>>> tonhue
    List<Menu> findAll();

    //    @Query(value = "select u from Menu u where u.status = 1 and u.id = :id")
    Optional<Menu> findById(Long id);

}
