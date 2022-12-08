package com.altek.intro.mapper;

import com.altek.intro.dto.request.PageTranslateRequestDto;
import com.altek.intro.entity.Menu;
import com.altek.intro.entity.Page;
import com.altek.intro.entity.PageTranslate;
import org.springframework.stereotype.Component;

@Component
public interface PageMapper extends AbstractMapper {

    Page convertToEntity(Page page, PageTranslateRequestDto requestDto, Menu menu);

    PageTranslate convertToEntity(PageTranslate pageTranslate, PageTranslateRequestDto requestDto, Page page);
}
