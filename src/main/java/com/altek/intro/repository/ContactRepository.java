package com.altek.intro.repository;

import com.altek.intro.entites.ContactEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends  AbstractRepository<ContactEntity, Long>{
    @Query(value = "SELECT * FROM ALT_CONTACT where STATUS = 1 ", nativeQuery = true)
   List<ContactEntity> findAll();

}
