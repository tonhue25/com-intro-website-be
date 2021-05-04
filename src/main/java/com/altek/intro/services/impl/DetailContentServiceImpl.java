package com.altek.intro.services.impl;

import com.altek.intro.dto.DetailContentDTO;
import com.altek.intro.dto.DetailContentViewDTO;
import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.entites.DetailContentEntity;
import com.altek.intro.entites.MenuEntity;
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
    public List<DetailContentViewDTO> getAllDetailContent() throws Exception {
        try {
            List<DetailContentViewDTO> detailContentViewDTOs = new ArrayList<DetailContentViewDTO>();

            List<DetailContentEntity> detailContentEntities = detailRepository.findAll();
            DetailContentViewDTO dto = new DetailContentViewDTO();
            if (CollectionUtils.isNotEmpty(detailContentEntities)) {
                detailContentViewDTOs = detailContentEntities.stream().map(item -> (DetailContentViewDTO) detailMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return detailContentViewDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<DetailContentViewDTO> getAllDetailContentByContentId(Long contentID) throws Exception {
        try {
            List<DetailContentViewDTO> detailContentViewDTOs = new ArrayList<DetailContentViewDTO>();

            List<DetailContentEntity> detailContentEntities = detailRepository.findAllByContentId(contentID);
            DetailContentViewDTO dto = new DetailContentViewDTO();
            if (CollectionUtils.isNotEmpty(detailContentEntities)) {
                detailContentViewDTOs = detailContentEntities.stream().map(item -> (DetailContentViewDTO) detailMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return detailContentViewDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public DetailContentViewDTO getDetailContentById(Long id) throws Exception {
        try {
            DetailContentViewDTO detailContentViewDTO = new DetailContentViewDTO();
            DetailContentEntity detailContentEntity = detailRepository.findById(id).get();
            detailContentViewDTO = (DetailContentViewDTO) detailMapper.convertToDTO(detailContentViewDTO,detailContentEntity);
            return detailContentViewDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
