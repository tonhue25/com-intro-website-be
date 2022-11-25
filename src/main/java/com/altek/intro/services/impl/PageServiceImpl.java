package com.altek.intro.services.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.PageRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.PageResponseDto;
import com.altek.intro.entities.Menu;
import com.altek.intro.entities.Page;
import com.altek.intro.entities.PageTranslate;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.PageMapper;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.PageRepository;
import com.altek.intro.repository.PageTranslateRepository;
import com.altek.intro.services.PageService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl extends AbstractServiceImpl implements PageService {

    @Autowired
    private PageRepository pageContentRepository;

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

    public int returnValidPageNumber(Integer pageNumber, Integer pageSize){
        if(pageNumber <= 1){
            return 1;
        }
        int maxPage = (pageContentRepository.findAll().size() / pageSize) + 1;
        if(pageNumber > maxPage){
            return maxPage;
        }
        return pageNumber;
    }

    public void isNullPageNumberAndPageSize(Integer pageNumber, Integer pageSize){
        if( pageSize == null || pageNumber == null){
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", pageNumber));// sửa throw lại nè.
        }
    }

    @Override
    public BaseResponse getAllPageContentByMenuId(PageRequestDto requestBody) {
        Optional<Menu> optional = menuRepository.findById(requestBody.getMenuId());
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", requestBody.getMenuId()));
        }
        isNullPageNumberAndPageSize(requestBody.getPage(), requestBody.getSize());

        Integer pageSize = requestBody.getSize();
        Integer pageNumber = returnValidPageNumber(requestBody.getPage(), requestBody.getSize());

        Pageable pageable = getPageable(pageNumber - 1, pageSize);
        List<PageResponseDto> listEntity =
                pageContentTranslateRepository.findAllPageContentByMenuID(requestBody.getLanguage(), requestBody.getMenuId(), pageable);
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
            Optional<Page> optional = pageContentRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }

        entity = (Page) pageContentMapper.convertToEntity(request, entity);
        entity.setMenu(menuEntity);
        entity = pageContentRepository.save(entity);
        PageResponseDto response = modelMapper.map(entity, PageResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.page", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<Page> optional = pageContentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("page.not.found.with.id:%s", id));
        }
        Page entity = optional.get();
        if(entity.getStatus().equals(Constant.DELETE)){
            return new BaseResponse(Constant.SUCCESS, String.format("page.already.delete.with.id:%s", id),
                    String.format("status.of.page.content:%s", entity.getStatus()));
        }
        entity.setStatus(Constant.DELETE);
        entity = pageContentRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.page.with.id:%s", id),
                String.format("status.of.page.content:%s", entity.getStatus()));
    }

//    @Override
//    public BaseResponse listPageContent(BaseRequest requestDto) {
//        if (DataUtil.isEmpty(requestDto.getPage())) {
//            throw new IllegalArgumentException("page.is.invalid");
//        }
//        if (DataUtil.isEmpty(requestDto.getSize())) {
//            throw new IllegalArgumentException("size.is.invalid");
//        }
//        if (DataUtil.isEmpty(requestDto.getId())) {
//            throw new IllegalArgumentException("menu.id.is.invalid");
//        }
//        Optional<Menu> menuOptional = menuRepository.findById(requestDto.getId());
//        if (!menuOptional.isPresent()) {
//            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", requestDto.getId()));
//        }
//        Menu menu = menuOptional.get();
//        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
//        if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
//            Sort.Direction sort = Sort.Direction.ASC;
//            if (requestDto.getSortType().equals("DESC")) {
//                sort = Sort.Direction.DESC;
//            }
//            pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(), Sort.by(sort, requestDto.getSortBy()));
//        }
//        org.springframework.data.domain.Page<Page> pageEntity = pageContentRepository.getList(requestDto.getSearch(), menu, pageable);
//        List<Page> listEntity = pageEntity.getContent();
//        List<PageResponseDto> listDTO = new ArrayList<>();
//        PageResponseDto dto = new PageResponseDto();
//        if (CollectionUtils.isNotEmpty(listEntity)) {
//            listDTO = listEntity.stream().map(item -> (PageResponseDto) pageContentMapper.convertToDTO(dto, item)).collect(Collectors.toList());
//        }
//        ListResponseDto<PageResponseDto> response = listResponseMapper.setDataListResponse(listDTO, pageEntity, pageable);
//        return new BaseResponse(Constant.SUCCESS, "get.list.page", response);
//    }
}
