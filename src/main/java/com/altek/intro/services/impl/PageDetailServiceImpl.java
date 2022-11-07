package com.altek.intro.services.impl;

import com.altek.intro.dto.PageDetailDTO;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.entites.PageDetailEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageContentMapper;
import com.altek.intro.mapper.PageDetailMapper;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.repository.PageDetailRepository;
import com.altek.intro.services.PageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageDetailServiceImpl extends AbstractServiceImpl implements PageDetailService {

    @Autowired
    private PageDetailRepository pageDetailRepository;

    @Autowired
    private PageContentRepository pageContentRepository;

    @Autowired
    private PageDetailMapper pageDetailMapper;

    @Override
    public PageDetailDTO getByPageContentId(Long id) {
        Optional<PageContentEntity> optional = pageContentRepository.findById(id);
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("page.content.not.found.with.id:%s", id));
        }
        PageDetailDTO pageDetailDTO = new PageDetailDTO();
        Optional<PageDetailEntity> optionalPageDetail = pageDetailRepository.findByPageContentId(id);
        if(!optionalPageDetail.isPresent()){
            throw new ResourceNotFoundException(String.format("page.content.no.have.page.detail.id:%s", id));
        }
        PageDetailDTO dto = (PageDetailDTO) pageDetailMapper.convertToDTO(pageDetailDTO,optionalPageDetail.get());
        return dto;
    }
}
