package com.altek.intro.repository;

import com.altek.intro.entities.ProductGroup;
import com.altek.intro.entities.RecruitmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends AbstractRepository<ProductGroup, Long> {

    @Query(value = "SELECT * FROM ALT_PRODUCT_GROUP where STATUS = 1 ", nativeQuery = true)
    List<ProductGroup> findAll();

}


