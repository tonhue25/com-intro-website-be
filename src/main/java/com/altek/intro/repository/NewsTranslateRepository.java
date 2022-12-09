package com.altek.intro.repository;

import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.dto.response.NewsResponseDto;
import com.altek.intro.entity.LeadershipTranslate;
import com.altek.intro.entity.News;
import com.altek.intro.entity.NewsTranslate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsTranslateRepository extends AbstractRepository<NewsTranslate, Long> {
    @Query("select new com.altek.intro.dto.response.NewsResponseDto("
            + " nt.id, nt.status, nt.createdBy, TO_CHAR (nt.createdTime, 'DD/MM/YYYY')," +
            " nt.lastUpdatedBy, TO_CHAR (nt.lastUpdatedTime, 'DD/MM/YYYY'), " +
            " nt.title, nt.languageId, nt.news.id as newsId ,nt.detail, " +
            " nt.shortDescription , n.thumbnail ) from NewsTranslate nt, News n "
            + " where n.status = 1 and nt.languageId = :language and nt.news.id = n.id "
            + " and (:search is null or lower(nt.title) like lower(concat(:search, '%'))) " +
            " and ( :startDate is null or  nt.createdTime >= TO_DATE(:startDate,'DD-MM-YY') ) " +
            " and ( :endDate is null or  nt.createdTime <=  TO_DATE(:endDate,'DD-MM-YY') )")
    Page<NewsResponseDto> getList(@Param("search") String search, @Param("language") String language,
                                         @Param("startDate") String startDate,
                                         @Param("endDate") String endDate,
                                         Pageable pageable);
    @Query("select new com.altek.intro.dto.response.NewsResponseDto ("
            + " nt.id, nt.status, nt.createdBy, TO_CHAR (nt.createdTime, 'DD/MM/YYYY'),  " +
            " nt.lastUpdatedBy, TO_CHAR (nt.lastUpdatedTime, 'DD/MM/YYYY') , " +
            " nt.title, nt.languageId, nt.news.id as newsId ,nt.detail, " +
            " nt.shortDescription , n.thumbnail ) from NewsTranslate nt, News n "
            + " where n.status = 1 and nt.languageId = :language and nt.news.id = n.id "
            + " and (:search is null or lower(nt.title) like lower(concat(:search, '%'))) " +
            " and ( :startDate is null or  nt.createdTime >= TO_DATE(:startDate,'DD-MM-YY') ) " +
            " and ( :endDate is null or  nt.createdTime <=  TO_DATE(:endDate,'DD-MM-YY') )")
    List<NewsResponseDto> getList(@Param("search") String search, @Param("language") String language,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);

    @Query("SELECT nt FROM NewsTranslate nt WHERE nt.languageId = :language AND nt.news.id = :newsId AND nt.news.status = 1 ")
    NewsTranslate findByNewsId(@Param("language") String language, @Param("newsId") Long newsId);

    @Query(value = "select lt from NewsTranslate lt " +
            "where lt.status = 1 and lt.news.id = :newsId and lt.languageId = :languageId")
    NewsTranslate findNewsTranslateByNewsIdAndLanguageId(@Param("newsId") Long newsId,
                                                        @Param("languageId") String languageId);

    @Query(value = "SELECT lt FROM NewsTranslate lt WHERE lt.status = 1 AND lt.news.id = :newsId")
    List<NewsTranslate> findNewsTranslateByNewsId(@Param("newsId") Long newsId);

    @Query(value = "select new com.altek.intro.dto.response.NewsResponseDto(n.id, n.status, nt.createdBy, " +
            "TO_CHAR (nt.createdTime, 'dd/MM/yyyy'),  nt.lastUpdatedBy, TO_CHAR (nt.lastUpdatedTime, 'dd/MM/yyyy')," +
            " nt.title, nt.languageId, nt.news.id ,nt.detail, nt.shortDescription , n.thumbnail ) " +
            "from NewsTranslate nt inner join News n on nt.news.id = n.id " +
            "where n.status = 1 and nt.news.id = :newsId and nt.languageId = :languageId")
    NewsResponseDto findByNewsIdAndLanguageId(@Param("newsId") Long newsId, @Param("languageId") String languageId);

}
