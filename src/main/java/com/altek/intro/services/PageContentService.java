package com.altek.intro.services;

import com.altek.intro.dto.PageContentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageContentService extends AbstractService{
    List<PageContentDTO> getAllPageContent() throws Exception;
    List<PageContentDTO> getAllPageContentByMenuId(Long id) throws Exception;
}
