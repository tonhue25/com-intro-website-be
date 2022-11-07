package com.altek.intro.services.impl;

import com.altek.intro.dto.LeadershipDTO;
import com.altek.intro.dto.RecruitmentDTO;
import com.altek.intro.entites.LeadershipEntity;
import com.altek.intro.entites.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.LeadershipMapper;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.repository.LeadershipRepository;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.LeadershipService;
import com.altek.intro.services.RecruitmentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadershipServiceImpl extends AbstractServiceImpl implements LeadershipService {
    @Autowired
    private LeadershipRepository leadershipRepository;

    @Autowired
    private LeadershipMapper leadershipMapper;

    @Override
    public List<LeadershipDTO> getAllLeadership() {
        try {
            List<LeadershipDTO> leadershipDTOS = new ArrayList<LeadershipDTO>();

            List<LeadershipEntity> leadershipEntities = leadershipRepository.findAll();
            LeadershipDTO dto = new LeadershipDTO();
            if (CollectionUtils.isNotEmpty(leadershipEntities)) {
                leadershipDTOS = leadershipEntities.stream().map(item -> (LeadershipDTO) leadershipMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return leadershipDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
