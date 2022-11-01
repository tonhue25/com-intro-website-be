package com.altek.intro.repository;

import com.altek.intro.entites.DetailServiceEntity;
import com.altek.intro.entites.HistoryEntity;
import com.altek.intro.entites.MenuEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailServiceRepository extends  AbstractRepository<DetailServiceEntity, Long>{

    @Query(value = "SELECT * FROM ALT_DETAIL_SERVICE where ACTIVE_FLAG = 1 ", nativeQuery = true)
    List<DetailServiceEntity> findAll();
}
