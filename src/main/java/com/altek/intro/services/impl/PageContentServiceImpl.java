package com.altek.intro.services.impl;

import com.altek.intro.dto.PageContentDTO;
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
    public List<PageContentDTO> getAllPageContent() throws Exception {
        try {
            List<PageContentDTO> pageContentDTOs = new ArrayList<PageContentDTO>();

            List<PageContentEntity> pageContentEntities = pageContentRepository.findAll();
            PageContentDTO dto = new PageContentDTO();
            if (CollectionUtils.isNotEmpty(pageContentEntities)) {
                pageContentDTOs = pageContentEntities.stream().map(item -> (PageContentDTO) pageContentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return pageContentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<PageContentDTO> getAllPageContentByMenuId(int menuId) {
        try {
            List<PageContentDTO> pageContentDTOs = new ArrayList<PageContentDTO>();

            List<PageContentEntity> pageContentEntities = pageContentRepository.findAllByMenuId(menuId);
            PageContentDTO dto = new PageContentDTO();
            if (CollectionUtils.isNotEmpty(pageContentEntities)) {
                pageContentDTOs = pageContentEntities.stream().map(item -> (PageContentDTO) pageContentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return pageContentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public PageContentEntity getPageContentById(Long id) throws Exception {
        try {
            PageContentEntity PageContentEntities = pageContentRepository.findById(id).get();
            return PageContentEntities;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
