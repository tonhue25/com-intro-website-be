package com.altek.intro.service.impl;

import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.request.PageTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageResponseDto;
import com.altek.intro.dto.response.PageTranslateResponseDto;
import com.altek.intro.entity.Menu;
import com.altek.intro.entity.Page;
import com.altek.intro.entity.PageTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
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
    private PageTranslateRepository pageContentTranslateRepository;
    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PageTranslateRepository pageTranslateRepository;

    @Autowired
    private PageMapper pageMapper;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getAllPageContent(String lang) {
        try {
            List<PageResponseDto> listDto = new ArrayList<>();
            List<PageTranslate> listEntity = pageContentTranslateRepository.findAllPageContent(lang);
            PageResponseDto dto = new PageResponseDto();
            if (CollectionUtils.isNotEmpty(listEntity)) {
                listDto = listEntity.stream().map(item -> (PageResponseDto) pageMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return new BaseResponse(Constant.SUCCESS, "get.all.page", listDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Integer initializePageNumber(Integer pageNumber) {
        if (pageNumber == null) {
            return Constant.FIRST_PAGE;
        } else {
            if (pageNumber < 1) return Constant.FIRST_PAGE;
            return pageNumber;
        }
    }

    public Integer initializePageSize(Integer pageSize) {
        if (pageSize == null) {
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
        List<PageResponseDto> listEntity = pageContentTranslateRepository.findAllPageContentByMenuID(requestBody.getLanguage(), requestBody.getMenuId(), paging);
        return new BaseResponse(Constant.SUCCESS, "get.list.page.by.menuId", listEntity);
    }

    @Override
    public BaseResponse getPage(Long id, String lang) {
        Optional<Page> optional = pageRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.not.found.with.id:%s", id));
        }
        Page page = optional.get();
        PageResponseDto responseDto = modelMapper.map(page, PageResponseDto.class);
        List<PageTranslateResponseDto> pageTranslateResponseDtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(page.getPageTranslates())) {
            pageTranslateResponseDtos = page.getPageTranslates().stream().map(item -> modelMapper.map(item, PageTranslateResponseDto.class)).collect(Collectors.toList());
        }
        responseDto.setTranslateResponseDtos(pageTranslateResponseDtos);
        return new BaseResponse(Constant.SUCCESS, "get.page", responseDto);
    }

    @Override
    public BaseResponse createUpdatePage(PageTranslateRequestDto request) {
        Menu menu = new Menu();
        Optional<Menu> optionalMenu = menuRepository.findById(request.getMenuId());
        if (!optionalMenu.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", request.getMenuId()));
        }
        menu = optionalMenu.get();
        Page page = new Page();
        if (!DataUtil.isEmpty(request.getPageId())) {
            Optional<Page> optionalPage = pageRepository.findById(request.getPageId());
            if (optionalPage.isPresent()) {
                page = optionalPage.get();
            }
        }
        PageTranslate pageTranslate = new PageTranslate();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<PageTranslate> optionalPageTranslate = pageTranslateRepository.findById(request.getId());
            if (optionalPageTranslate.isPresent()) {
                pageTranslate = optionalPageTranslate.get();
            }
        }
        page = pageMapper.convertToEntity(page, request, menu);
        page = pageRepository.save(page);
        pageTranslate = pageMapper.convertToEntity(pageTranslate, request, page);
        pageTranslateRepository.save(pageTranslate);
        PageTranslateResponseDto responseDto = modelMapper.map(pageTranslate, PageTranslateResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, "create.or.update.page", responseDto);
    }

    @Override
    public BaseResponse deletePage(Long id) {
        Optional<Page> optional = pageRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.not.found.with.id:%s", id));
        }
        Page page = optional.get();
        page.setStatus(Constant.DELETE);
        pageRepository.save(page);
        page.getPageTranslates().stream().forEach(item -> item.setStatus(Constant.DELETE));
        pageTranslateRepository.saveAll(page.getPageTranslates());
        return new BaseResponse(Constant.SUCCESS, "delete.page", Constant.SUCCESS);
    }
}
