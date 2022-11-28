package com.altek.intro.service.impl;

import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.ProductGroupResponseDto;
import com.altek.intro.entity.ProductGroup;
import com.altek.intro.repository.ProductGroupRepository;
import com.altek.intro.service.ProductGroupService;
import com.altek.intro.util.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductGroupServiceImpl extends AbstractServiceImpl implements ProductGroupService {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getAll() {
        ListResponseDto<ProductGroupResponseDto> response = new ListResponseDto<>();
        List<ProductGroupResponseDto> responseList = new ArrayList<>();
        List<ProductGroup> menus = productGroupRepository.findAll();
        if (CollectionUtils.isNotEmpty(menus)) {
            responseList = menus.stream().map(item -> modelMapper.map(item, ProductGroupResponseDto.class))
                    .collect(Collectors.toList());
        }
        response.setList(responseList);
        response.setTotalPages(1);
        response.setPage(1);
        response.setSize(responseList.size());
        response.setRecordPerPage(responseList.size());
        return new BaseResponse(Constant.SUCCESS, "get.list.product.group", response);
    }

}
