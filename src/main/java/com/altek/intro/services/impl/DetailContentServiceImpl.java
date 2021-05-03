package com.altek.intro.services.impl;

import com.altek.intro.dto.DetailContentDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.DetailContentEntity;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.DetailMapper;
import com.altek.intro.mapper.PageContentMapper;
import com.altek.intro.repository.DetailRepository;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.services.DetailContentService;
import com.altek.intro.services.PageContentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailContentServiceImpl extends AbstractServiceImpl implements DetailContentService {
    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private DetailMapper detailMapper;
    @Override
    public List<DetailContentDTO> getAllDetailContent() throws Exception {
        try {
            List<DetailContentDTO> detailContentDTOs = new ArrayList<DetailContentDTO>();

            List<DetailContentEntity> detailContentEntities = detailRepository.findAll();
            DetailContentDTO dto = new DetailContentDTO();
            if (CollectionUtils.isNotEmpty(detailContentEntities)) {
                detailContentDTOs = detailContentEntities.stream().map(item -> (DetailContentDTO) detailMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return detailContentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<DetailContentDTO> getAllDetailContentByContentId(Long contentID) throws Exception {
        try {
            List<DetailContentDTO> detailContentDTOs = new ArrayList<DetailContentDTO>();

            List<DetailContentEntity> detailContentEntities = detailRepository.findAllByContentId(contentID);
            DetailContentDTO dto = new DetailContentDTO();
            if (CollectionUtils.isNotEmpty(detailContentEntities)) {
                detailContentDTOs = detailContentEntities.stream().map(item -> (DetailContentDTO) detailMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return detailContentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public DetailContentEntity getDetailContentById(Long id) throws Exception {
        try {
            DetailContentEntity detailContentEntity = detailRepository.findById(id).get();
            return detailContentEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
