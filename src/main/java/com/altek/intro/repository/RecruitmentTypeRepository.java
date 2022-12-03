package com.altek.intro.repository;

import com.altek.intro.dto.response.RecruitmentTypeResponseDto;
import com.altek.intro.entity.RecruitmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentTypeRepository extends AbstractRepository<RecruitmentType, Long> {

    @Query(value = "SELECT * FROM ALT_RECRUITMENT_TYPE where STATUS = 1 ", nativeQuery = true)
    List<RecruitmentType> findAll();

    @Query("select new com.altek.intro.dto.response.RecruitmentTypeResponseDto( "
            + " nt.id, nt.status, nt.createdBy, TO_CHAR (nt.createdTime, 'DD/MM/YYYY'),  " +
            " nt.lastUpdatedBy, TO_CHAR (nt.lastUpdatedTime, 'DD/MM/YYYY') , " +
            " nt.name,nt.languageId, nt.recruitmentType.id as recruitmentTypeId ) from RecruitmentTypeTranslate nt, RecruitmentType n "
            + " where n.status = 1 and nt.languageId = :language and nt.recruitmentType.id = n.id ")
    List<RecruitmentTypeResponseDto> getAll(@Param("language") String language);
    @Query(value = "select rt from RecruitmentType rt where status = 1 and id = :id")
    Optional<RecruitmentType> findById(Long id);

}


