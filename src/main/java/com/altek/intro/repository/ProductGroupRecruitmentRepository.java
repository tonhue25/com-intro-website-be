package com.altek.intro.repository;

import com.altek.intro.entity.ProductGroup;
import com.altek.intro.entity.ProductgroupRecruitment;
import com.altek.intro.entity.Recruitment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductGroupRecruitmentRepository extends AbstractRepository<ProductgroupRecruitment, Long> {
    Optional<ProductgroupRecruitment> findByProductGroupAndRecruitment(ProductGroup productGroup, Recruitment recruitment);
}


