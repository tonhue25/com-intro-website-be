package com.altek.intro.repository;

import com.altek.intro.entites.CompanyInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyInfoRepository extends  AbstractRepository<CompanyInfoEntity, Long>{
    @Query(value = "SELECT * FROM ALT_COMPANY_INFO where ACTIVE_FLAG = 1 ", nativeQuery = true)
    List<CompanyInfoEntity> findAll();
}
