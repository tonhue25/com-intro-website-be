package com.altek.intro.repository;

import com.altek.intro.entities.SliderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepository extends AbstractRepository<SliderEntity, Long>{

    @Query(value = "SELECT * FROM ALT_SLIDER where ACTIVE_FLAG = 1", nativeQuery = true)
    List<SliderEntity> findAll();

}
