package com.altek.intro.service.impl;

import com.altek.intro.dto.response.*;
import com.altek.intro.repository.RecruitmentTypeRepository;
import com.altek.intro.service.RecruitmentTypeService;
import com.altek.intro.util.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentTypeServiceImpl extends AbstractServiceImpl implements RecruitmentTypeService {

    @Autowired
    private RecruitmentTypeRepository recruitmentTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getAll(String language) {
        ListResponseDto<RecruitmentTypeResponseDto> response = new ListResponseDto<>();
        List<RecruitmentTypeResponseDto> responseList = recruitmentTypeRepository.getAll(language);
        response.setLanguage(language);
        response.setList(responseList);
        response.setTotalPages(1);
        response.setPage(1);
        response.setSize(responseList.size());
        response.setRecordPerPage(responseList.size());
        return new BaseResponse(Constant.SUCCESS, "get.list.recruitment.type", response);
    }

}
