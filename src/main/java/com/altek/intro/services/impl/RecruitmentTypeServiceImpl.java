package com.altek.intro.services.impl;

import com.altek.intro.dto.response.*;
import com.altek.intro.entities.Menu;
import com.altek.intro.entities.RecruitmentType;
import com.altek.intro.repository.RecruitmentTypeRepository;
import com.altek.intro.services.RecruitmentTypeService;
import com.altek.intro.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentTypeServiceImpl extends AbstractServiceImpl implements RecruitmentTypeService {

    @Autowired
    private RecruitmentTypeRepository recruitmentTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getAll() {
        ListResponseDto<RecruitmentTypeResponseDto> response = new ListResponseDto<>();
        List<RecruitmentTypeResponseDto> responseList = new ArrayList<RecruitmentTypeResponseDto>();
        List<RecruitmentType> menus = recruitmentTypeRepository.findAll();
        if (CollectionUtils.isNotEmpty(menus)) {
            responseList = menus.stream().map(item -> modelMapper.map(item, RecruitmentTypeResponseDto.class))
                    .collect(Collectors.toList());
        }
        response.setList(responseList);
        response.setTotalPages(1);
        response.setPage(1);
        response.setSize(responseList.size());
        response.setRecordPerPage(responseList.size());
        return new BaseResponse(Constant.SUCCESS, "get.list.recruitment.type", response);
    }

}
