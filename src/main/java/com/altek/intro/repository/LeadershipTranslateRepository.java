package com.altek.intro.repository;

import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.entity.LeadershipTranslate;
import com.altek.intro.enums.EmployeeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadershipTranslateRepository extends AbstractRepository<LeadershipTranslate, Long> {
    @Query(value = "select u from Leadership u where u.status = 1 and u.id = :id")
    Optional<LeadershipTranslate> findById(Long id);

    @Query(value = "SELECT new com.altek.intro.dto.response.LeadershipResponseDto(l.id, l.status, l.createdBy, " +
            "TO_CHAR (l.createdTime , 'DD/MM/YYYY'), l.lastUpdatedBy, TO_CHAR(l.lastUpdatedTime,'dd/MM/YYYY'), l.image, " +
            "lt.fullName, lt.position, lt.information, l.facebook, l.linkedIn, lt.languageId, lt.leadershipId, l.team) " +
            "FROM LeadershipTranslate lt, Leadership l WHERE lt.leadershipId = l.id and " +
            "l.status = 1 and lt.languageId = :language and (l.team in (:teams))")
    Page<LeadershipResponseDto> getListLeadership(@Param("language") String language, @Param("teams") List<EmployeeType> types, Pageable pageable);

    @Query(value = "SELECT new com.altek.intro.dto.response.LeadershipResponseDto(l.id, l.status, l.createdBy, " +
            "TO_CHAR (l.createdTime , 'DD/MM/YYYY'), l.lastUpdatedBy, TO_CHAR(l.lastUpdatedTime,'dd/MM/YYYY'), l.image, " +
            "lt.fullName, lt.position, lt.information, l.facebook, l.linkedIn, lt.languageId, lt.leadershipId, l.team) " +
            "from LeadershipTranslate lt inner join Leadership l on lt.leadershipId = l.id " +
            "where lt.status = 1 and lt.leadershipId = :leadershipId and lt.languageId = :languageId")
    LeadershipResponseDto findByLeadershipIdAndLanguageId(@Param("leadershipId") Long leadershipId, @Param("languageId") String languageId);

    LeadershipTranslate findLeadershipTranslateByLeadershipIdAndLanguageId(@Param("leadershipId") Long leadershipId, @Param("languageId") String languageId);

    @Query(value = "SELECT lt FROM LeadershipTranslate lt WHERE lt.status = 1 AND lt.leadershipId = :leadershipId")
    List<LeadershipTranslate> findLeadershipTranslateByLeadershipId(@Param("leadershipId") Long leadershipId);

}
