package com.altek.intro.services.impl;

import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.RecruitmentDTO;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.entites.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.MenuMapper;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.model.RecruitmentModel;
import com.altek.intro.repository.MenuRepository;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.MenuService;
import com.altek.intro.services.RecruitmentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<RecruitmentDTO> getAllRecruitment() {
        try {
            List<RecruitmentDTO> recruitmentDTOS = new ArrayList<RecruitmentDTO>();

            List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAll();
            RecruitmentDTO dto = new RecruitmentDTO();
            if (CollectionUtils.isNotEmpty(recruitmentEntities)) {
                recruitmentDTOS = recruitmentEntities.stream().map(item -> (RecruitmentDTO) recruitmentMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return recruitmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
