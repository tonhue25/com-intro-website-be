package com.altek.intro.services.impl;

import com.altek.intro.dto.request.PageContentRequestDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.PageContentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.PageContentMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.services.PageContentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageContentServiceImpl extends AbstractServiceImpl implements PageContentService {

    @Autowired
    private PageContentRepository pageContentRepository;

    @Autowired
    private PageContentMapper pageContentMapper;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<PageContentRequestDTO> getAll() {
        try {
            List<PageContentRequestDTO> listDto = new ArrayList<>();
            List<PageContentEntity> listEntity = pageContentRepository.findAll();
            PageContentRequestDTO dto = new PageContentRequestDTO();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (PageContentRequestDTO) pageContentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return listDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<PageContentRequestDTO> listPageContentByMenuId(Long id) {
        Optional<MenuEntity> optional = menuRepository.findById(id);
        List<PageContentRequestDTO> listDTO = new ArrayList<>();
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
        }
        PageContentRequestDTO dto = new PageContentRequestDTO();
        List<PageContentEntity> listEntity = pageContentRepository.findByMenuId(id);
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream().map(item -> (PageContentRequestDTO) pageContentMapper.convertToDTO(dto, item))
                    .collect(Collectors.toList());
        }
        return listDTO;
    }
}
