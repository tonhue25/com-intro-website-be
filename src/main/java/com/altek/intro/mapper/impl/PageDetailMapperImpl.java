package com.altek.intro.mapper.impl;

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.entities.PageContentEntity;
import com.altek.intro.entities.PageDetailEntity;
import com.altek.intro.mapper.PageDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageDetailMapperImpl extends AbstractMapperImpl implements PageDetailMapper{

    @Override
    public PageDetailEntity convertToEntity(PageDetailEntity entity, PageDetailRequestDTO dto, PageContentEntity pageContent) {
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setShortDescription(dto.getShortDescription());
        entity.setContent(dto.getContent());
        entity.setThumbnail(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setPageContent(pageContent);
        return entity;
    }
}
