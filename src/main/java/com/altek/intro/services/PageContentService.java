package com.altek.intro.services;

import com.altek.intro.dto.request.PageContentRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageContentService extends AbstractService{

    List<PageContentRequestDTO> getAll();

    List<PageContentRequestDTO> listPageContentByMenuId(Long id);
}
