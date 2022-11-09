package com.altek.intro.services.impl;

import com.altek.intro.dto.request.RecruitmentRequestDTO;
import com.altek.intro.entites.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.RecruitmentMapper;

import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.RecruitmentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentServiceImpl extends AbstractServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Override
    public List<RecruitmentRequestDTO> getAllRecruitment() {
        try {
            List<RecruitmentRequestDTO> recruitmentDTOS = new ArrayList<RecruitmentRequestDTO>();

            List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAll();
            RecruitmentRequestDTO dto = new RecruitmentRequestDTO();
            if (CollectionUtils.isNotEmpty(recruitmentEntities)) {
                recruitmentDTOS = recruitmentEntities.stream().map(item -> (RecruitmentRequestDTO) recruitmentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return recruitmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public RecruitmentRequestDTO Create(RecruitmentRequestDTO request) {
        RecruitmentEntity entity = new RecruitmentEntity();
        entity = (RecruitmentEntity) recruitmentMapper.convertToEntity(request,entity);
        return null;
    }
}
