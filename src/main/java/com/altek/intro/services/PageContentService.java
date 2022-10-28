package com.altek.intro.services;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.dto.PageContentViewDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageContentService extends AbstractService{
    List<PageContentViewDTO> getAllPageContent() throws Exception;
    List<PageContentViewDTO> getAllPageContentByMenuCode(String menuCode) throws Exception;
    PageContentViewDTO getPageContentById(Long id) throws Exception;
}
