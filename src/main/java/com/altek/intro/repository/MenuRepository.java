package com.altek.intro.repository;

import com.altek.intro.entities.MenuEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends  AbstractRepository<MenuEntity, Long>{
    @Query(value = "SELECT * FROM ALT_MENU where STATUS = 1 ", nativeQuery = true)
    List<MenuEntity> findAll();

}
