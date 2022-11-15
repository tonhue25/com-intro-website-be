package com.altek.intro.services.impl;

import java.util.Optional;

import com.altek.intro.utils.DataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageDetailResponseDTO;
import com.altek.intro.entities.Page;
import com.altek.intro.entities.PageDetail;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageDetailMapper;
import com.altek.intro.repository.PageRepository;
import com.altek.intro.repository.PageDetailRepository;
import com.altek.intro.services.PageDetailService;
import com.altek.intro.utils.Constant;

@Service
public class PageDetailServiceImpl extends AbstractServiceImpl implements PageDetailService {

    @Autowired
    private PageDetailRepository pageDetailRepository;

    @Autowired
    private PageRepository pageContentRepository;

    @Autowired
    private PageDetailMapper pageDetailMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponse getByPage(Long id) {
        Optional<Page> optional = pageContentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.not.found.with.id:%s", id));
        }
        Optional<PageDetail> optionalPageDetail = pageDetailRepository.findByPage(optional.get());
        if (!optionalPageDetail.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.no.have.page.detail.id:%s", id));
        }
        PageDetailResponseDTO response = modelMapper.map(optionalPageDetail.get(), PageDetailResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, "get.page.detail.by.page.id", response);
    }

    @Override
    public BaseResponse create(PageDetailRequestDto request) {
        Optional<Page> optional = pageContentRepository.findById(request.getPageContentId());
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(
                    String.format("page.content.not.found.with.id:%s", request.getPageContentId()));
        }
        PageDetail entity = new PageDetail();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<PageDetail> optionalPageDetail = pageDetailRepository.findById(request.getId());
            if (optionalPageDetail.isPresent()) {
                entity = optionalPageDetail.get();
            }
        } else {
            if (pageDetailRepository.existsByPage(optional.get())) {
                throw new ResourceNotFoundException(
                        String.format("page.content.already.exists.with.id:%s", request.getPageContentId()));
            }
        }
        entity = pageDetailMapper.convertToEntity(entity, request, optional.get());
        entity = pageDetailRepository.save(entity);
        PageDetailResponseDTO response = modelMapper.map(entity, PageDetailResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.page.detail", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<PageDetail> optional = pageDetailRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.detail.not.found.with.id:%s", id));
        }
        PageDetail entity = optional.get();
        if (entity.getStatus().equals(Constant.DELETE)) {
            return new BaseResponse(Constant.SUCCESS, String.format("page.detail.already.delete.with.id:%s",
                    String.format("status.of.page.detail:%s", entity.getStatus())));
        }
        entity.setStatus(Constant.DELETE);
        entity = pageDetailRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.page.detail.with.id:%s", id),
                String.format("status.of.page.detail:%s", entity.getStatus()));
    }
}
