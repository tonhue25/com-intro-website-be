package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entity.*;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.repository.*;
import com.altek.intro.service.RecruitmentService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruitmentServiceImpl extends AbstractServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RecruitmentTypeRepository recruitmentTypeRepository;

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private RecruitmentTranslateRepository recruitmentTranslateRepository;

    @Autowired
    private ProductGroupRecruitmentRepository productGroupRecruitmentRepository;

    @Override
    public BaseResponse getListRecruitment(BaseRequest request) {
        try {
            List<String> locations = new ArrayList<>();
            List<Long> types = new ArrayList<>();
            List<Long> groups = new ArrayList<>();
            if (DataUtil.isEmpty(request.getLocations())) {
                locations = Arrays.asList(Constant.HCM, Constant.HN, Constant.HCMC);
            } else {
                locations = request.getLocations();
            }
            if (DataUtil.isEmpty(request.getTypes())) {
                types = recruitmentTypeRepository.findAll().stream()
                        .map(RecruitmentType::getId)
                        .collect(Collectors.toList());
            } else {
                types = request.getTypes();
            }
            if (DataUtil.isEmpty(request.getGroups())) {
                groups = productGroupRepository.findAll().stream()
                        .map(ProductGroup::getId)
                        .collect(Collectors.toList());
            } else {
                groups = request.getGroups();
            }
            Pageable pageable = getPageable(request);
            Page<RecruitmentResponseDto> page = recruitmentRepository.getList(request.getSearch(), request.getLanguage(), locations, types, groups, pageable);
            if (!CollectionUtils.isNotEmpty(page.getContent())) {
                return new BaseResponse(Constant.SUCCESS, "get.list.recruitment", Constant.EMPTY_LIST);
            }
            ListResponseDto<RecruitmentResponseDto> response = new ListResponseDto<>(pageable, page, page.getContent(), request.getLanguage());
            return new BaseResponse(Constant.SUCCESS, "get.list.recruitment", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.recruitment", e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse createOrUpdateRecruitment(RecruitmentRequestDto request) {
        try {
            RecruitmentTranslate recruitmentTranslate = new RecruitmentTranslate();
            RecruitmentType recruitmentType = new RecruitmentType();
            Recruitment recruitment = new Recruitment();
            if (!DataUtil.isEmpty(request.getId())) {
                Optional<RecruitmentTranslate> optionalRecruitmentTranslate = recruitmentTranslateRepository.findById(request.getId());
                if (optionalRecruitmentTranslate.isPresent()) {
                    recruitmentTranslate = optionalRecruitmentTranslate.get();
                    recruitment = recruitmentTranslate.getRecruitment();
                    recruitmentType = recruitment.getRecruitmentType();
                }
            }
            Optional<RecruitmentType> optionalRecruitmentType = recruitmentTypeRepository.findById(request.getRecruitmentTypeId());
            if (!optionalRecruitmentType.isPresent()) {
                throw new ResourceNotFoundException(String.format("recruitment.type.not.found.with.id:%s", request.getRecruitmentTypeId()));
            }
            recruitmentType = optionalRecruitmentType.get();
            Optional<Recruitment> optionalRecruitment = recruitmentRepository.findById(request.getRecruitmentId());
            if (optionalRecruitment.isPresent()) {
                recruitment = optionalRecruitment.get();
            }
            recruitment = modelMapper.map(request, Recruitment.class);
            recruitment.setId(request.getRecruitmentId());
            recruitment = recruitmentRepository.save(recruitment);
            recruitmentTranslate = modelMapper.map(request, RecruitmentTranslate.class);
            recruitmentTranslate.setRecruitment(recruitment);
            recruitmentTranslateRepository.save(recruitmentTranslate);
            // product-group.
            Optional<ProductGroup> optionalProductGroup =
                    productGroupRepository.findById(request.getProductGroupId());
            if (!optionalProductGroup.isPresent()) {
                throw new ResourceNotFoundException(String.format("product.group.not.found.with.id:%s", request.getProductGroupId()));
            }
            ProductGroup productGroup = optionalProductGroup.get();
            Optional<ProductgroupRecruitment> optional = productGroupRecruitmentRepository.findByProductGroupAndRecruitment(productGroup, recruitment);
            if (!optional.isPresent()) {
                ProductgroupRecruitment productgroupRecruitment = new ProductgroupRecruitment(Constant.ACTIVE, productGroup, recruitment);
                productGroupRecruitmentRepository.save(productgroupRecruitment);
            }
            return new BaseResponse(Constant.SUCCESS, "create.or.update.recruitment", Constant.SUCCESS);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.or.update.recruitment", e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteRecruitment(Long id) {
        try {
            Optional<Recruitment> optional = recruitmentRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("recruitment.not.found.with.id:%s", id));
            }
            Recruitment recruitment = optional.get();
            recruitment.setStatus(Constant.DELETE);
            recruitment = recruitmentRepository.save(recruitment);
            if (recruitment.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.recruitment",Constant.SUCCESS);
            }
            return new BaseResponse(Constant.FAIL, "delete.recruitment",Constant.FAIL);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.recruitment.type", e.getMessage());
        }
    }
}
