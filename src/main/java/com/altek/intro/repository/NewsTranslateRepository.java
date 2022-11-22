package com.altek.intro.repository;

import com.altek.intro.entities.News;
import com.altek.intro.entities.NewsTranslate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsTranslateRepository extends AbstractRepository<NewsTranslate, Long> {
    @Query("select e from NewsTranslate e where  e.status = 1 and (:search is null or " +
            "   ( lower(e.title) like lower(concat(:search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat(:search, '%')) )) ")
    Page<NewsTranslate> getList(@Param("search") String search,
                       Pageable pageable);

    @Query("select e from NewsTranslate e where  e.status = 1 and (:search is null or " +
            "   ( lower(e.title) like lower(concat(:search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat(:search, '%')) )) ")
    List<NewsTranslate> getAll(@Param("search") String search);

    @Query("select e from NewsTranslate e where  e.status = 1 and (:search is null or " +
            "   ( lower(e.title) like lower(concat(:search, '%')) )) " +
            " and ( :startDate is null or  createdTime >= TO_DATE(:startDate,'DD-MM-YY') ) " +
            " and ( :endDate is null or  createdTime <=  TO_DATE(:endDate,'DD-MM-YY') )" )
    Page<NewsTranslate> getListNewsNew(@Param("search") String search,
                              @Param("startDate") String startDate,
                              @Param("endDate") String endDate,
                              Pageable pageable);

    @Query("select e from NewsTranslate e where  e.status = 1 and (:search is null or " +
            "   ( lower(e.title) like lower(concat(:search, '%'))  or " +
            "   lower(e.shortDescription) like lower(concat(:search, '%')) )) "+
            " and ( :startDate is null or  createdTime >= TO_DATE(:startDate,'DD-MM-YY') ) " +
            " and ( :endDate is null or  createdTime <=  TO_DATE(:endDate,'DD-MM-YY') )" )
    List<NewsTranslate> getAllNewsNew(@Param("search") String search,
                             @Param("startDate") String startDate,
                             @Param("endDate") String endDate);
}
