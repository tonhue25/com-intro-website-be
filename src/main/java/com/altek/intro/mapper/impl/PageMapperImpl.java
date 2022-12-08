package com.altek.intro.mapper.impl;

import com.altek.intro.dto.request.PageTranslateRequestDto;
import com.altek.intro.entity.Menu;
import com.altek.intro.entity.Page;
import com.altek.intro.entity.PageTranslate;
import com.altek.intro.mapper.PageMapper;
import org.springframework.stereotype.Component;

@Component
public class PageMapperImpl extends AbstractMapperImpl implements PageMapper {
    public Page convertToEntity(Page page, PageTranslateRequestDto requestDto, Menu menu) {
        page.setImage(requestDto.getImage());
        page.setTimeline(requestDto.getTimeline());
        page.setUrl(requestDto.getUrl());
        page.setMenu(menu);
        page.setIcon(requestDto.getIcon());
        page.setStatus(requestDto.getStatus());
        return page;
    }

    public PageTranslate convertToEntity(PageTranslate pageTranslate, PageTranslateRequestDto requestDto, Page page) {
        pageTranslate.setStatus(requestDto.getStatus());
        pageTranslate.setPageTitle(requestDto.getPageTitle());
        pageTranslate.setShortDescription(requestDto.getShortDescription());
        pageTranslate.setLanguageId(requestDto.getLanguageId());
        pageTranslate.setDetail(requestDto.getDetail());
        pageTranslate.setPage(page);
        return pageTranslate;
    }
}
