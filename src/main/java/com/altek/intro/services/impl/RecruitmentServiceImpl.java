package com.altek.intro.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.RecruitmentDTO;
import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entites.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.RecruitmentService;

@Service
public class RecruitmentServiceImpl extends AbstractServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Override
    public List<RecruitmentDTO> getAllRecruitment() {
        try {
            List<RecruitmentDTO> recruitmentDTOS = new ArrayList<RecruitmentDTO>();
            List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAll();
            RecruitmentDTO dto = new RecruitmentDTO();
            if (CollectionUtils.isNotEmpty(recruitmentEntities)) {
                recruitmentDTOS = recruitmentEntities.stream()
                        .map(item -> (RecruitmentDTO) recruitmentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return recruitmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public ListResponseDto<RecruitmentDTO> getList(ListRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
        Page<RecruitmentEntity> pageEntity = recruitmentRepository.getList(requestDto.getSearch().toLowerCase(),
                pageable);
        List<RecruitmentEntity> listEntity = pageEntity.getContent();
        RecruitmentDTO dto = new RecruitmentDTO();
        List<RecruitmentDTO> listDTO = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream().map(item -> (RecruitmentDTO) recruitmentMapper.convertToDTO(dto, item))
                    .collect(Collectors.toList());
        }
        ListResponseDto<RecruitmentDTO> responseDto = new ListResponseDto<>();
        responseDto.setData(listDTO);
        responseDto.setSize(pageEntity.getNumberOfElements());
        responseDto.setRecordPerPage(pageable.getPageSize());
        responseDto.setTotalPages(pageEntity.getTotalPages());
        responseDto.setPage(pageable.getPageNumber()+1);
        return responseDto;
    }
}
