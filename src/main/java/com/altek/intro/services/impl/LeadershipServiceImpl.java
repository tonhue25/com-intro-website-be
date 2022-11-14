package com.altek.intro.services.impl;


import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import com.altek.intro.entities.Leadership;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.LeadershipMapper;
import com.altek.intro.repository.LeadershipRepository;
import com.altek.intro.services.LeadershipService;
import com.altek.intro.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LeadershipServiceImpl extends AbstractServiceImpl implements LeadershipService {
    @Autowired
    private LeadershipRepository leadershipRepository;

    @Autowired
    private LeadershipMapper leadershipMapper;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<LeadershipResponseDTO> getAllLeadership() {
        try {
            List<LeadershipResponseDTO> leadershipDTOS = new ArrayList<LeadershipResponseDTO>();
            List<Leadership> leadershipEntities = leadershipRepository.findAll();
            LeadershipResponseDTO dto = new LeadershipResponseDTO();
            if (CollectionUtils.isNotEmpty(leadershipEntities)) {
                leadershipDTOS = leadershipEntities.stream().map(item -> (LeadershipResponseDTO) leadershipMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return leadershipDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipResponseDTO create(LeadershipRequestDto request) {
        Leadership entity = new Leadership();
        entity = (Leadership) leadershipMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = leadershipRepository.save(entity);
        LeadershipResponseDTO response = modelMapper.map(entity, LeadershipResponseDTO.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipResponseDTO delete(Long id) {
        Optional<Leadership> optional = leadershipRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("Leadership.not.found.with.id:%s", id));
        }
        Leadership entity = optional.get();
        entity.setStatus(Constant.DELETE);
        leadershipRepository.save(entity);
        LeadershipResponseDTO response = modelMapper.map(entity, LeadershipResponseDTO.class);
        return response;
    }
}
