package com.altek.intro.mapper.impl;

import com.altek.intro.dto.request.PageDetailRequestDto;
import com.altek.intro.entity.Page;
import com.altek.intro.entity.PageDetail;
import com.altek.intro.mapper.PageDetailMapper;
import org.springframework.stereotype.Component;

@Component
public class PageDetailMapperImpl extends AbstractMapperImpl implements PageDetailMapper{

    @Override
    public PageDetail convertToEntity(PageDetail entity, PageDetailRequestDto dto, Page pageContent) {
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setShortDescription(dto.getShortDescription());
        entity.setContent(dto.getContent());
        entity.setThumbnail(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setPage(pageContent);
        return entity;
    }
}
