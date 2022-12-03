package com.altek.intro.repository;

import com.altek.intro.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends  AbstractRepository<Menu, Long>{
    @Query(value = "SELECT * FROM ALT_MENU where STATUS = 1 ", nativeQuery = true)

    List<Menu> findAll();
    Optional<Menu> findById(Long id);

}
