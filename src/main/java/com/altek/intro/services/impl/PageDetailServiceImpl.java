package com.altek.intro.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageDetailResponseDTO;
import com.altek.intro.entities.PageContentEntity;
import com.altek.intro.entities.PageDetailEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageDetailMapper;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.repository.PageDetailRepository;
import com.altek.intro.services.PageDetailService;
import com.altek.intro.utils.Constant;

@Service
public class PageDetailServiceImpl extends AbstractServiceImpl implements PageDetailService {

    @Autowired
    private PageDetailRepository pageDetailRepository;

    @Autowired
    private PageContentRepository pageContentRepository;

    @Autowired
    private PageDetailMapper pageDetailMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PageDetailResponseDTO getByPageContentId(Long id) {
        Optional<PageContentEntity> optional = pageContentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.content.not.found.with.id:%s", id));
        }
        PageDetailResponseDTO pageDetailDTO = new PageDetailResponseDTO();
        Optional<PageDetailEntity> optionalPageDetail = pageDetailRepository.findByPageContent(optional.get());
        if (!optionalPageDetail.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.content.no.have.page.detail.id:%s", id));
        }
        PageDetailResponseDTO dto = (PageDetailResponseDTO) pageDetailMapper.convertToDTO(pageDetailDTO, optionalPageDetail.get());
        return dto;
    }

    @Override
    public BaseResponse create(PageDetailRequestDTO request) {
        Optional<PageContentEntity> optional = pageContentRepository.findById(request.getPageContentId());
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(
                    String.format("page.content.not.found.with.id:%s", request.getPageContentId()));
        }
        PageDetailEntity entity = new PageDetailEntity();
        entity = (PageDetailEntity) pageDetailMapper.convertToEntity(request, entity);
        entity = pageDetailRepository.save(entity);
        PageDetailResponseDTO response = modelMapper.map(entity, PageDetailResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.page.detail", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<PageDetailEntity> optional = pageDetailRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.detail.not.found.with.id:%s", id));
        }
        PageDetailEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        entity = pageDetailRepository.save(entity);
        PageDetailResponseDTO response = modelMapper.map(entity, PageDetailResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.page.detail.with.id:%s", id),
                response);
    }
}
