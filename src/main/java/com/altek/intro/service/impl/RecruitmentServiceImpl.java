package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entity.ProductGroup;
import com.altek.intro.entity.Recruitment;
import com.altek.intro.entity.RecruitmentType;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.repository.ProductGroupRepository;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.repository.RecruitmentTypeRepository;
import com.altek.intro.service.RecruitmentService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    @Override
    public BaseResponse getList(BaseRequest request) {
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

}
