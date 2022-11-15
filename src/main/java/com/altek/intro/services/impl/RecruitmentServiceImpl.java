package com.altek.intro.services.impl;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entities.Recruitment;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.RecruitmentService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruitmentServiceImpl extends AbstractServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ListResponseMapper<RecruitmentResponseDto, Recruitment> listResponseMapper;

    @Override
    public List<RecruitmentResponseDto> getAllRecruitment() {
        try {
            List<RecruitmentResponseDto> recruitmentDTOS = new ArrayList<RecruitmentResponseDto>();
            List<Recruitment> recruitmentEntities = recruitmentRepository.findAll();
            RecruitmentResponseDto dto = new RecruitmentResponseDto();
            if (CollectionUtils.isNotEmpty(recruitmentEntities)) {
                recruitmentDTOS = recruitmentEntities.stream()
                        .map(item -> (RecruitmentResponseDto) recruitmentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return recruitmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse getList(ListRequestDto requestDto) {
        if (DataUtil.isEmpty(requestDto.getPage())) {
            throw new IllegalArgumentException("page.is.invalid");
        }
        if (DataUtil.isEmpty(requestDto.getSize())) {
            throw new IllegalArgumentException("size.is.invalid");
        }
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
        if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
            Sort.Direction sort = Sort.Direction.ASC;
            if (requestDto.getSortType().equals("DESC")) {
                sort = Sort.Direction.DESC;
            }
            pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(),
                    Sort.by(sort, requestDto.getSortBy()));
        }
        Page<Recruitment> pageEntity = recruitmentRepository.getList(requestDto.getSearch(),
                pageable);
        List<Recruitment> listEntity = pageEntity.getContent();
        List<RecruitmentResponseDto> listResponse = new ArrayList<>();
        RecruitmentResponseDto dto = new RecruitmentResponseDto();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listResponse = recruitmentMapper.mapList(listEntity);
        }
        ListResponseDto<RecruitmentResponseDto> response = listResponseMapper.setDataListResponse(listResponse,
                pageEntity, pageable);
        return new BaseResponse(Constant.SUCCESS, "get.list.recruitment", response);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public RecruitmentResponseDto create(RecruitmentRequestDto request) {
        Recruitment entity = new Recruitment();
        entity = (Recruitment) recruitmentMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = recruitmentRepository.save(entity);
        RecruitmentResponseDto response = modelMapper.map(entity, RecruitmentResponseDto.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public RecruitmentResponseDto delete(Long id) {
        Optional<Recruitment> optional = recruitmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("Recruitment.not.found.with.id:%s", id));
        }
        Recruitment entity = optional.get();
        entity.setStatus(Constant.DELETE);
        recruitmentRepository.save(entity);
        RecruitmentResponseDto response = modelMapper.map(entity, RecruitmentResponseDto.class);
        return response;
    }
}
