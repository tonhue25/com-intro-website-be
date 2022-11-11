package com.altek.intro.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.entites.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.RecruitmentService;
import com.altek.intro.utils.DataUtil;

@Service
public class RecruitmentServiceImpl extends AbstractServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Override
    public List<RecruitmentResponseDTO> getAllRecruitment() {
        try {
            List<RecruitmentResponseDTO> recruitmentDTOS = new ArrayList<RecruitmentResponseDTO>();
            List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAll();
            RecruitmentResponseDTO dto = new RecruitmentResponseDTO();
            if (CollectionUtils.isNotEmpty(recruitmentEntities)) {
                recruitmentDTOS = recruitmentEntities.stream()
                        .map(item -> (RecruitmentResponseDTO) recruitmentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return recruitmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Autowired
    ListResponseMapper<RecruitmentResponseDTO, RecruitmentEntity> listResponseMapper;

    @Override
    public ListResponseDto<RecruitmentResponseDTO> getList(ListRequestDto requestDto) {
        if (DataUtil.isEmpty(requestDto.getPage())) {
            //
        }
        if (DataUtil.isEmpty(requestDto.getSize())) {
            //
        }
        Sort sort;
        if (requestDto.getSortType().equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, requestDto.getSortBy());
        } else {
            sort = Sort.by(Sort.Direction.ASC, requestDto.getSortBy());
        }
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(),
                sort);
        Page<RecruitmentEntity> pageEntity = recruitmentRepository.getList(requestDto.getSearch().toLowerCase(),
                pageable);
        List<RecruitmentEntity> listEntity = pageEntity.getContent();
        List<RecruitmentResponseDTO> listDTO = new ArrayList<>();
        RecruitmentResponseDTO dto = new RecruitmentResponseDTO();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream()
                    .map(item -> (RecruitmentResponseDTO) recruitmentMapper.convertToDTO(dto, item))
                    .collect(Collectors.toList());
        }
        ListResponseDto<RecruitmentResponseDTO> responseDto = listResponseMapper.setDataListResponse(listDTO,
                pageEntity, pageable);
        return responseDto;
    }
}
