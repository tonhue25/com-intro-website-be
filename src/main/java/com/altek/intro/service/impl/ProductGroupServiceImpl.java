package com.altek.intro.service.impl;

import com.altek.intro.dto.request.ProductGroupRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.ProductGroupResponseDto;
import com.altek.intro.entity.ProductGroup;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.repository.ProductGroupRepository;
import com.altek.intro.service.ProductGroupService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductGroupServiceImpl extends AbstractServiceImpl implements ProductGroupService {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getListProductGroup() {
        try {
            List<ProductGroup> listEntity = productGroupRepository.findAll();
            if (!CollectionUtils.isNotEmpty(listEntity)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.product.group", Constant.EMPTY_LIST);
            }
            List<ProductGroupResponseDto> listDto = listEntity.stream().map(item -> modelMapper.map(item, ProductGroupResponseDto.class)).collect(Collectors.toList());
            ListResponseDto<ProductGroupResponseDto> response = new ListResponseDto<>(listDto);
            return new BaseResponse(Constant.SUCCESS, "get.list.product.group", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.product.group", e.getMessage());
        }
    }

    @Override
    public BaseResponse createOrUpdate(ProductGroupRequestDto dto) {
        try {
            ProductGroup productGroup = new ProductGroup();
            if (!DataUtil.isEmpty(dto.getId())) {
                Optional<ProductGroup> optional = productGroupRepository.findById(dto.getId());
                if (optional.isPresent()) {
                    productGroup = optional.get();
                }
            }
            productGroup = modelMapper.map(dto, ProductGroup.class);
            productGroup = productGroupRepository.save(productGroup);
            ProductGroupResponseDto response = modelMapper.map(productGroup, ProductGroupResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, "create.or.update.product.group", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.or.update.product.group", e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try {
            ProductGroup productGroup = new ProductGroup();
            if (DataUtil.isEmpty(id)) {
                throw new MissingServletRequestParameterException(Constant.INVALID_REQUEST_PARAM.toString(), "delete.product.group");
            } else {
                Optional<ProductGroup> optional = productGroupRepository.findById(id);
                if (!optional.isPresent()) {
                    throw new ResourceNotFoundException(String.format("product.group.not.found.with.id:%s", id));
                }
                productGroup = optional.get();
                productGroup.setStatus(Constant.DELETE);
                productGroup = productGroupRepository.save(productGroup);
                if (productGroup.getStatus() == Constant.DELETE) {
                    return new BaseResponse(Constant.SUCCESS, "delete.product.group");
                } else {
                    return new BaseResponse(Constant.FAIL, "delete.product.group");
                }
            }
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.product.group", e.getMessage());
        }
    }
}
