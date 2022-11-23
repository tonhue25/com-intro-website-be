package com.altek.intro.repository;

import com.altek.intro.dto.response.RecruitmentTypeResponseDto;
import com.altek.intro.entities.RecruitmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentTypeRepository extends AbstractRepository<RecruitmentType, Long> {

    @Query(value = "SELECT * FROM ALT_RECRUITMENT_TYPE where STATUS = 1 ", nativeQuery = true)
    List<RecruitmentType> findAll();

//    @Query("select rtt.* from alt_recruitment_type_translate rtt, alt_recruitment_type rt where " +
//            "rtt.recruitment_type_id = rt.id and rtt.language_id = :language and rt.status = 1")
//    List<RecruitmentTypeResponseDto> getAll(@Param("language") String language);

    @Query("select new com.altek.intro.dto.response.RecruitmentTypeResponseDto( "
            + " nt.id, nt.status, nt.createdBy, TO_CHAR (nt.createdTime, 'DD/MM/YYYY'),  " +
            " nt.lastUpdatedBy, TO_CHAR (nt.lastUpdatedTime, 'DD/MM/YYYY') , " +
            " nt.name,nt.languageId, nt.recruitmentType.id as recruitmentTypeId ) from RecruitmentTypeTranslate nt, RecruitmentType n "
            + " where n.status = 1 and nt.languageId = :language and nt.recruitmentType.id = n.id ")
    List<RecruitmentTypeResponseDto> getAll(@Param("language") String language);

}


