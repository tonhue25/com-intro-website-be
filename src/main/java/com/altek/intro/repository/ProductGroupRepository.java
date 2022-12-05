package com.altek.intro.repository;

import com.altek.intro.entity.ProductGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductGroupRepository extends AbstractRepository<ProductGroup, Long> {

    @Query(value = "SELECT * FROM ALT_PRODUCT_GROUP where STATUS = 1 ", nativeQuery = true)
    List<ProductGroup> findAll();

    @Query(value = "select p from ProductGroup p where p.status = 1 and p.id = :id")
    Optional<ProductGroup> findById(@Param("id") Long id);

}


