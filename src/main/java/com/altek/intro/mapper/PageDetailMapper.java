package com.altek.intro.mapper;

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.entities.PageContentEntity;
import com.altek.intro.entities.PageDetailEntity;
import org.springframework.stereotype.Component;

@Component
public interface PageDetailMapper extends  AbstractMapper{
//    public PageDetailEntity convertToEntity(PageDetailRequestDTO dto, PageContentEntity pageContent);

    public PageDetailEntity convertToEntity(PageDetailEntity entity, PageDetailRequestDTO dto, PageContentEntity pageContent);
}
