package com.altek.intro.mapper;

import com.altek.intro.dto.request.PageDetailRequestDto;
import com.altek.intro.entities.Page;
import com.altek.intro.entities.PageDetail;
import org.springframework.stereotype.Component;

@Component
public interface PageDetailMapper extends  AbstractMapper{
    PageDetail convertToEntity(PageDetail entity, PageDetailRequestDto dto, Page pageContent);
}
