package com.altek.intro.services;

import com.altek.intro.dto.DetailContentDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.DetailContentEntity;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailContentService extends AbstractService{
    List<DetailContentDTO> getAllDetailContent() throws Exception;
    List<DetailContentDTO> getAllDetailContentByContentId(Long contentID) throws Exception;
    DetailContentEntity getDetailContentById(Long id) throws Exception;
}
