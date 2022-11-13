package com.altek.intro.repository;

import com.altek.intro.entites.NewsEntity;
import com.altek.intro.entites.RecruitmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends AbstractRepository<NewsEntity, Long> {
    @Query("select e from NewsEntity e where  e.status = 1 and " +
            "   ( lower(e.title) like lower(concat('%', :search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat('%', :search, '%')) )")
    Page<NewsEntity> getList(@Param("search") String search,
                             Pageable pageable);
}
