package com.altek.intro.services;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PageContentService extends AbstractService{

    List<PageContentDTO> getAll();

    List<PageContentDTO> listPageContentByMenuId(Long id);
}
