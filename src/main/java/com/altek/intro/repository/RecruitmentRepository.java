package com.altek.intro.repository;

import java.util.List;

import com.altek.intro.dto.response.RecruitmentResponseDto;
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

    @Query("select new com.altek.intro.dto.response.RecruitmentResponseDto("
            + " rt.id, rt.status,rt.createdBy,  TO_CHAR (rt.createdTime, 'DD/MM/YYYY'), " +
            " rt.lastUpdatedBy ,  TO_CHAR (rt.lastUpdatedTime, 'DD/MM/YYYY'),  rt.jobTitle,  " +
            " rt.jobDescription,r.image, r.fileLink," +
            " rt.location, r.recruitmentType.id as recruitmentTypeId, rt.languageId , rt.recruitment.id as recruitmentId , pgr.productGroup.id as  productGroupId) " +
            " from RecruitmentTranslate rt , Recruitment r , ProductgroupRecruitment pgr "
            + " where r.id = rt.recruitment.id " +
            " and pgr.recruitment.id = r.id " +
            " and r.status = 1 " +
            " and rt.languageId = :language " +
            " and rt.location in (:locations) " +
            " and r.recruitmentType.id in (:types) " +
            " and pgr.productGroup.id in (:groups) " +
            " and (lower(rt.jobTitle) like lower(concat(:search, '%')))")
    List<RecruitmentResponseDto> getList(@Param("search") String search,
                                     @Param("language") String language,
                                     @Param("locations") List<String> locations,
                                     @Param("types") List<Long> types,
                                     @Param("groups") List<Long> groups);

    @Query("select new com.altek.intro.dto.response.RecruitmentResponseDto("
            + " rt.id, rt.status,rt.createdBy,  TO_CHAR (rt.createdTime, 'DD/MM/YYYY'), " +
            " rt.lastUpdatedBy ,  TO_CHAR (rt.lastUpdatedTime, 'DD/MM/YYYY'),  rt.jobTitle,  " +
            " rt.jobDescription,r.image, r.fileLink," +
            " rt.location, r.recruitmentType.id as recruitmentTypeId, rt.languageId , rt.recruitment.id as recruitmentId , pgr.productGroup.id as  productGroupId) " +
            " from RecruitmentTranslate rt , Recruitment r , ProductgroupRecruitment pgr "
            + " where r.id = rt.recruitment.id " +
            " and pgr.recruitment.id = r.id " +
            " and r.status = 1 " +
            " and rt.languageId = :language " +
            " and rt.location in (:locations) " +
            " and r.recruitmentType.id in (:types) " +
            " and pgr.productGroup.id in (:groups) " +
            " and (lower(rt.jobTitle) like lower(concat(:search, '%')))")
    Page<RecruitmentResponseDto> getList(@Param("search") String search,
                                     @Param("language") String language,
                                     @Param("locations") List<String> locations,
                                     @Param("types") List<Long> types,
                                     @Param("groups") List<Long> groups, Pageable pageable);

}
