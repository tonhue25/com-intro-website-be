package com.altek.intro.repository;

import com.altek.intro.entity.Contact;
import com.altek.intro.enums.ContactType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends AbstractRepository<Contact, Long> {
    @Query(value = "SELECT * FROM ALT_CONTACT where STATUS = 1 ", nativeQuery = true)
    List<Contact> findAll();

    @Query("select e from Contact e where  e.status = 1 and (:search is null or ( " +
            "  lower(e.fullName) like  lower( concat('%',:search, '%')) or" +
            "  lower(e.phoneNumber) like  lower( concat('%',:search, '%')) or" +
            "  lower(e.email) like  lower( concat('%',:search, '%'))  ))" +
            " and (type in (:types))")
    Page<Contact> getList(@Param("search") String search, @Param("types") List<ContactType> types,
                          Pageable pageable);

    @Query("select e from Contact e where  e.status = 1 and (:search is null or ( " +
            "  lower(e.fullName) like  lower( concat('%',:search, '%')) or" +
            "  lower(e.phoneNumber) like  lower( concat('%',:search, '%')) or" +
            "  lower(e.email) like  lower( concat('%',:search, '%'))  ))" +
            " and (type in (:types))")
    List<Contact> getList(@Param("search") String search, @Param("types") List<ContactType> types);

    @Query(value = "select u from Contact u where u.status = 1 and u.id = :id")
    Optional<Contact> findById(Long id);

    @Query(value = "select u from Contact u where u.status = 1 and u.phoneNumber = :phoneNumber")
    Optional<Contact> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "select u from Contact u where u.status = 1 and ((u.email = :email) or " +
            " (u.phoneNumber = :phoneNumber))" +
            " and (u.type = :type ) ")
    Optional<Contact> checkExist(@Param("email") String email, @Param("phoneNumber") String phoneNumber,
                                         @Param("type") ContactType type);
}
