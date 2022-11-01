package com.altek.intro.repository;

import com.altek.intro.entites.HistoryEntity;
import com.altek.intro.entites.MenuEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends  AbstractRepository<HistoryEntity, Long>{

    @Query(value = "SELECT * FROM ALT_HISTORY where ACTIVE_FLAG = 1 ", nativeQuery = true)
    List<HistoryEntity> findAll();

}
