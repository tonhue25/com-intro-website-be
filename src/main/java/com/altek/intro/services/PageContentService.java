package com.altek.intro.services;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageContentService extends AbstractService{
    List<PageContentDTO> getAllPageContent() throws Exception;
    List<PageContentDTO> getAllPageContentByMenuId(Long menuId) throws Exception;
    PageContentEntity getPageContentById(Long id) throws Exception;
}
