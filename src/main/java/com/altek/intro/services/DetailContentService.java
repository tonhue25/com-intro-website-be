package com.altek.intro.services;

import com.altek.intro.dto.DetailContentDTO;
import com.altek.intro.dto.DetailContentViewDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.DetailContentEntity;
import com.altek.intro.entites.PageContentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailContentService extends AbstractService{
    List<DetailContentViewDTO> getAllDetailContent() throws Exception;
    List<DetailContentViewDTO> getAllDetailContentByContentId(Long contentID) throws Exception;
    DetailContentViewDTO getDetailContentById(Long id) throws Exception;
}
