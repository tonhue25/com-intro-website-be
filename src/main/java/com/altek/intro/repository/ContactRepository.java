package com.altek.intro.repository;

import com.altek.intro.entities.ContactEntity;
import com.altek.intro.entities.RecruitmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends AbstractRepository<ContactEntity, Long> {
    @Query(value = "SELECT * FROM ALT_CONTACT where STATUS = 1 ", nativeQuery = true)
    List<ContactEntity> findAll();

    @Query("select e from ContactEntity e where  e.status = 1 and (:search is null or ( " +
            "  lower(e.name) like  lower( concat('%',:search, '%')) or" +
            "  lower(e.phoneNumber) like  lower( concat('%',:search, '%')) or" +
            "  lower(e.email) like  lower( concat('%',:search, '%')) " +
            " ))")
    Page<ContactEntity> getList(@Param("search") String search,
                                Pageable pageable);
}
