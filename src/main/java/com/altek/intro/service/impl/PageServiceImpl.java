package com.altek.intro.service.impl;

import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageResponseDto;
import com.altek.intro.entity.Menu;
import com.altek.intro.entity.Page;
import com.altek.intro.entity.PageTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.PageMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.PageRepository;
import com.altek.intro.repository.PageTranslateRepository;
import com.altek.intro.service.PageService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl extends AbstractServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageTranslateRepository pageContentTranslateRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageMapper pageContentMapper;

    @Autowired
    ListResponseMapper<PageResponseDto, Page> listResponseMapper;

    @Override
    public BaseResponse getAllPageContent(String lang) {
        try {
            List<PageResponseDto> listDto = new ArrayList<>();
            List<PageTranslate> listEntity = pageContentTranslateRepository.findAllPageContent(lang);
            PageResponseDto dto = new PageResponseDto();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (PageResponseDto) pageContentMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return new BaseResponse(Constant.SUCCESS, "get.all.page", listDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Integer initializePageNumber(Integer pageNumber){
        if(pageNumber == null){
            return Constant.FIRST_PAGE;
        } else {
            if(pageNumber < 1)
                return Constant.FIRST_PAGE;
            return pageNumber;
        }
    }

    public Integer initializePageSize(Integer pageSize){
        if(pageSize == null){
            return Constant.MAX_SIZE;
        }
        return pageSize;
    }

    @Override
    public BaseResponse getAllPageContentByMenuId(PageRequestDto requestBody) {
        Optional<Menu> optional = menuRepository.findById(requestBody.getMenuId());
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", requestBody.getMenuId()));
        }

        Integer pageSize = initializePageSize(requestBody.getSize());
        Integer pageNumber = initializePageNumber(requestBody.getPage());

        Pageable paging = getPageable(pageNumber - 1, pageSize);
        List<PageResponseDto> listEntity =
                pageContentTranslateRepository.findAllPageContentByMenuID(requestBody.getLanguage(), requestBody.getMenuId(),paging);
        return new BaseResponse(Constant.SUCCESS, "get.list.page.by.menuId", listEntity);
    }

    @Override
    public BaseResponse create(PageRequestDto request) {
        Optional<Menu> optionalMenu = menuRepository.findById(request.getMenuId());
        if (!optionalMenu.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", request.getMenuId()));
        }
        Menu menuEntity = optionalMenu.get();
        Page entity = new Page();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<Page> optional = pageRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (Page) pageContentMapper.convertToEntity(request, entity);
        entity.setMenu(menuEntity);
        entity = pageRepository.save(entity);
        PageResponseDto response = modelMapper.map(entity, PageResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.page", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<Page> optional = pageRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.not.found.with.id:%s", id));
        }
        Page entity = optional.get();
        entity.setStatus(Constant.DELETE);
        entity = pageRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.page.with.id:%s", id),
                String.format("status.of.page.content:%s", entity.getStatus()));
    }
}
