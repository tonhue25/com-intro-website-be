package com.altek.intro.services.impl;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.PageContentRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.PageContentResponseDTO;
import com.altek.intro.entities.MenuEntity;
import com.altek.intro.entities.PageContentEntity;
import com.altek.intro.entities.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.PageContentMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.PageContentRepository;
import com.altek.intro.services.PageContentService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageContentMapper pageContentMapper;


    @Autowired
    ListResponseMapper<PageContentResponseDTO, PageContentEntity> listResponseMapper;

    @Override
    public BaseResponse getAll() {
        try {
            List<PageContentResponseDTO> listDto = new ArrayList<>();
            List<PageContentEntity> listEntity = pageContentRepository.findAll();
            PageContentResponseDTO dto = new PageContentResponseDTO();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (PageContentResponseDTO) pageContentMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return new BaseResponse(Constant.SUCCESS, "get.all.page.content", listDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse listPageContentByMenuId(Long id) {
        Optional<MenuEntity> optional = menuRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
        }
        List<PageContentResponseDTO> listDTO = new ArrayList<>();
        PageContentResponseDTO dto = new PageContentResponseDTO();
        List<PageContentEntity> listEntity = pageContentRepository.findByMenuAndStatus(optional.get(), 1);
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream().map(item -> (PageContentResponseDTO) pageContentMapper.convertToDTO(dto, item)).collect(Collectors.toList());
        }
        return new BaseResponse(Constant.SUCCESS, "get.list.page.content.by.menuId", listDTO);
    }

    @Override
    public BaseResponse create(PageContentRequestDTO request) {
        Optional<MenuEntity> optionalMenu = menuRepository.findById(request.getMenuId());
        if (!optionalMenu.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", request.getMenuId()));
        }

        MenuEntity menuEntity = optionalMenu.get();
        PageContentEntity entity = new PageContentEntity();

        if (!DataUtil.isEmpty(request.getId())) {
            Optional<PageContentEntity> optional = pageContentRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }

        entity = (PageContentEntity) pageContentMapper.convertToEntity(request, entity);
        entity.setMenu(menuEntity);
        entity = pageContentRepository.save(entity);
        PageContentResponseDTO response = modelMapper.map(entity, PageContentResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.page.content", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<PageContentEntity> optional = pageContentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.content.not.found.with.id:%s", id));
        }
        PageContentEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        entity = pageContentRepository.save(entity);
        PageContentResponseDTO response = modelMapper.map(entity, PageContentResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.page.content.with.id:%s", id), response);
    }

    @Override
    public BaseResponse listPageContent(ListRequestDto requestDto) {
        if (DataUtil.isEmpty(requestDto.getPage())) {
//
        }
        if (DataUtil.isEmpty(requestDto.getSize())) {
//
        }
        if (DataUtil.isEmpty(requestDto.getId())) {
//
        }
        Optional<MenuEntity> menuOptional = menuRepository.findById(requestDto.getId());
        if (!menuOptional.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", requestDto.getId()));
        }
        MenuEntity menu = menuOptional.get();
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
        if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
            Sort.Direction sort = Sort.Direction.ASC;
            if (requestDto.getSortType().equals("DESC")) {
                sort = Sort.Direction.DESC;
            }
            pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(), Sort.by(sort, requestDto.getSortBy()));
        }
        Page<PageContentEntity> pageEntity = pageContentRepository.getList(requestDto.getSearch(), menu, pageable);
        List<PageContentEntity> listEntity = pageEntity.getContent();
        List<PageContentResponseDTO> listDTO = new ArrayList<>();
        PageContentResponseDTO dto = new PageContentResponseDTO();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream().map(item -> (PageContentResponseDTO) pageContentMapper.convertToDTO(dto, item)).collect(Collectors.toList());
        }
        ListResponseDto<PageContentResponseDTO> response = listResponseMapper.setDataListResponse(listDTO, pageEntity, pageable);
        return new BaseResponse(Constant.SUCCESS, "get.list.page.content", response);
    }

}
