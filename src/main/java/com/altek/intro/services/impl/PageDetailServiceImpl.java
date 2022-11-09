package com.altek.intro.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.PageDetailDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.entites.PageDetailEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageDetailMapper;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.repository.PageDetailRepository;
import com.altek.intro.services.PageDetailService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

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

    @Autowired
    private ResponseUtil responseUtil;

    @Override
    public PageDetailDTO getByPageContentId(Long id) {
        Optional<PageContentEntity> optional = pageContentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.content.not.found.with.id:%s", id));
        }
        PageDetailDTO pageDetailDTO = new PageDetailDTO();
        Optional<PageDetailEntity> optionalPageDetail = pageDetailRepository.findByPageContentId(id);
        if (!optionalPageDetail.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.content.no.have.page.detail.id:%s", id));
        }
        PageDetailDTO dto = (PageDetailDTO) pageDetailMapper.convertToDTO(pageDetailDTO, optionalPageDetail.get());
        return dto;
    }

    @Override
    public BaseResponse create(PageDetailDTO request) {
        try {
            Optional<PageContentEntity> optional = pageContentRepository.findById(request.getPageContentId());
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(
                        String.format("page.content.not.found.with.id:%s", request.getPageContentId()));
            }
            PageDetailEntity entity = new PageDetailEntity();
            entity = (PageDetailEntity) pageDetailMapper.convertToEntity(request, entity);
            entity = pageDetailRepository.save(entity);
            PageDetailDTO response = modelMapper.map(entity, PageDetailDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, "add.or.update.page.detail", response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM, "ex.common.system.error.");
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try {
            Optional<PageDetailEntity> optional = pageDetailRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("page.detail.not.found.with.id:%s", id));
            }
            PageDetailEntity entity = optional.get();
            entity.setStatus(Constant.DELETE);
            entity = pageDetailRepository.save(entity);
            PageDetailDTO response = modelMapper.map(entity, PageDetailDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, String.format("delete.page.detail.with.id:%s", id),
                    response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
        }
    }
}
