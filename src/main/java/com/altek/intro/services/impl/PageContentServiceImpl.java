package com.altek.intro.services.impl;

import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.dto.PageContentViewDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageContentMapper;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.services.PageContentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageContentServiceImpl extends AbstractServiceImpl implements PageContentService {

    @Autowired
    private PageContentRepository pageContentRepository;

    @Autowired
    private PageContentMapper pageContentMapper;

    @Override
    public List<PageContentViewDTO> getAllPageContent() throws Exception {
        try {
            List<PageContentViewDTO> pageContentDTOs = new ArrayList<PageContentViewDTO>();

            List<PageContentEntity> pageContentEntities = pageContentRepository.findAll();
            PageContentViewDTO dto = new PageContentViewDTO();
            if (CollectionUtils.isNotEmpty(pageContentEntities)) {
                pageContentDTOs = pageContentEntities.stream().map(item -> (PageContentViewDTO) pageContentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return pageContentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<PageContentViewDTO> getAllPageContentByMenuCode(String menuCode) {
        String menuCodeLower = menuCode.toLowerCase();
        try {
            List<PageContentViewDTO> pageContentDTOs = new ArrayList<PageContentViewDTO>();
            List<PageContentEntity> pageContentEntities = pageContentRepository.findAllByMenuCode(menuCodeLower);
            PageContentViewDTO dto = new PageContentViewDTO();
            if (CollectionUtils.isNotEmpty(pageContentEntities)) {
                pageContentDTOs = pageContentEntities.stream().map(item -> (PageContentViewDTO) pageContentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return pageContentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public PageContentViewDTO getPageContentById(Long id) throws Exception {
        try {
            PageContentViewDTO pageContentViewDTO = new PageContentViewDTO();
            PageContentEntity pageContentEntity = pageContentRepository.findById(id).get();
            pageContentViewDTO = (PageContentViewDTO) pageContentMapper.convertToDTO(pageContentViewDTO,pageContentEntity);
            return pageContentViewDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
