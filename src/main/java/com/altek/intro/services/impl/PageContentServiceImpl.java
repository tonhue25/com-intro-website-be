package com.altek.intro.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageContentMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.services.PageContentService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

@Service
public class PageContentServiceImpl extends AbstractServiceImpl implements PageContentService {

    @Autowired
    private PageContentRepository pageContentRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageContentMapper pageContentMapper;

    @Autowired
    private ResponseUtil responseUtil;

    @Override
    public List<PageContentDTO> getAll() {
        try {
            List<PageContentDTO> listDto = new ArrayList<>();
            List<PageContentEntity> listEntity = pageContentRepository.findAll();
            PageContentDTO dto = new PageContentDTO();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (PageContentDTO) pageContentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return listDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<PageContentDTO> listPageContentByMenuId(Long id) {
        Optional<MenuEntity> optional = menuRepository.findById(id);
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
        }
        List<PageContentDTO> listDTO = new ArrayList<>();
        PageContentDTO dto = new PageContentDTO();
        List<PageContentEntity> listEntity = pageContentRepository.findByMenuIdAndStatus(id,1);
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream().map(item -> (PageContentDTO) pageContentMapper.convertToDTO(dto, item))
                    .collect(Collectors.toList());
        }
        return listDTO;
    }

    @Override
    public BaseResponse create(PageContentDTO request) {
        try {
            PageContentEntity entity = new PageContentEntity();
            entity = (PageContentEntity) pageContentMapper.convertToEntity(request, entity);
            entity = pageContentRepository.save(entity);
            PageContentDTO response = modelMapper.map(entity, PageContentDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, "add.or.update.page.content", response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM, "ex.common.system.error.");
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try {
            Optional<PageContentEntity> optional = pageContentRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("page.content.not.found.with.id:%s", id));
            }
            PageContentEntity entity = optional.get();
            entity.setStatus(Constant.DELETE);
            entity = pageContentRepository.save(entity);
            PageContentDTO response = modelMapper.map(entity, PageContentDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, String.format("delete.page.content.with.id:%s", id), response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
        }
    }
}
