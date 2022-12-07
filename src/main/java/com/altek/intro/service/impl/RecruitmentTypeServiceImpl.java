package com.altek.intro.service.impl;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.*;
import com.altek.intro.entity.ProductGroup;
import com.altek.intro.entity.Recruitment;
import com.altek.intro.entity.RecruitmentType;
import com.altek.intro.entity.RecruitmentTypeTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.RecruitmentTypeMapper;
import com.altek.intro.repository.RecruitmentTypeRepository;
import com.altek.intro.repository.RecruitmentTypeTranslateRepository;
import com.altek.intro.service.RecruitmentTypeService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentTypeServiceImpl extends AbstractServiceImpl implements RecruitmentTypeService {

    @Autowired
    private RecruitmentTypeRepository recruitmentTypeRepository;

    @Autowired
    private RecruitmentTypeTranslateRepository recruitmentTypeTranslateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RecruitmentTypeMapper recruitmentTypeMapper;

    @Override
    public BaseResponse getRecruitmentTypes(String language) {
        try {
            List<RecruitmentTypeResponseDto> responseDtos = recruitmentTypeRepository.getAll(language);
            if (!CollectionUtils.isNotEmpty(responseDtos)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.recruitment.type", Constant.EMPTY_LIST);
            }
            ListResponseDto<RecruitmentTypeResponseDto> response = new ListResponseDto<>(responseDtos, language);
            return new BaseResponse(Constant.SUCCESS, "get.list.recruitment.type", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.recruitment.type", e.getMessage());
        }
    }

    @Override
    public BaseResponse createOrUpdateRecruitmentType(RecruitmentTypeTranslateRequestDto request) {
        try {
            RecruitmentType recruitmentType = new RecruitmentType();
            if (!DataUtil.isEmpty(request.getRecruitmentTypeId())) {
                Optional<RecruitmentType> optionalRecruitment = recruitmentTypeRepository.findById(request.getRecruitmentTypeId());
                if (!optionalRecruitment.isPresent()) {
                    throw new ResourceNotFoundException(String.format("recruitment.type.not.found.with.id:%s", request.getRecruitmentTypeId()));
                }
                recruitmentType = optionalRecruitment.get();
            } else {
                recruitmentType.setStatus(request.getStatus());
                recruitmentType = recruitmentTypeRepository.save(recruitmentType);
            }
            RecruitmentTypeTranslate entity = new RecruitmentTypeTranslate();
            if (!DataUtil.isEmpty(request.getId())) {
                Optional<RecruitmentTypeTranslate> optional = recruitmentTypeTranslateRepository.findById(request.getId());
                if (optional.isPresent()) {
                    entity = optional.get();
                }
            }
            entity = recruitmentTypeMapper.convertDtoToEntity(entity, request, recruitmentType);
            entity = recruitmentTypeTranslateRepository.save(entity);
            RecruitmentTypeResponseDto response = modelMapper.map(entity, RecruitmentTypeResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, "create.or.update.recruitment.type", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.or.update.recruitment.type", e.toString());
        }
    }

    @Override
    public BaseResponse deleteRecruitmentType(Long id) {
        try {
            Optional<RecruitmentType> optional = recruitmentTypeRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("recruitment.type.not.found.with.id:%s", id));
            }
            RecruitmentType recruitmentType = optional.get();
            recruitmentType.setStatus(Constant.DELETE);
            recruitmentType = recruitmentTypeRepository.save(recruitmentType);
            if (recruitmentType.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.recruitment.type");
            }
            return new BaseResponse(Constant.FAIL, "delete.recruitment.type");
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.recruitment.type", e.getMessage());
        }
    }
}
