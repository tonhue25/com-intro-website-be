package com.altek.intro.repository;

import java.util.List;

import com.altek.intro.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altek.intro.entities.Recruitment;

@Repository
public interface RecruitmentRepository extends AbstractRepository<Recruitment, Long> {

    @Query(value = "SELECT * FROM ALT_RECRUITMENT where STATUS = 1 ", nativeQuery = true)
    List<Recruitment> findAll();

    @Query(value = "select r.* from ALT_RECRUITMENT r, ALT_PRODUCT_GROUP_RECRUITMENT p " +
            " where r.ID = p.RECRUITMENT_ID " +
            " and r.STATUS = 1 and (PRODUCT_GROUP_ID in ( :groups)) and ( " +
            " lower(JOB_TITLE) like lower( concat(:search, '%')) ) " +
            " and (LOCATION in ( :locations)) " +
            " and (RECRUITMENT_TYPE_ID in (:types)) ", nativeQuery = true)
    Page<Recruitment> getList(@Param("search") String search,
                              @Param("locations") List<String> locations,
                              @Param("types") List<Long> types,
                              @Param("groups") List<Long> groups,
                              Pageable pageable);
    @Query(value = "select r.* from ALT_RECRUITMENT r, ALT_PRODUCT_GROUP_RECRUITMENT p " +
            " where r.ID = p.RECRUITMENT_ID " +
            " and r.STATUS = 1 and (PRODUCT_GROUP_ID in ( :groups)) and ( " +
            " lower(JOB_TITLE) like lower( concat(:search, '%')) ) " +
            " and (LOCATION in ( :locations)) " +
            " and (RECRUITMENT_TYPE_ID in (:types)) ", nativeQuery = true)
    List<Recruitment> getAll(@Param("search") String search,
                             @Param("locations") List<String> locations,
                             @Param("types") List<Long> types,
                             @Param("groups") List<Long> groups);
}


