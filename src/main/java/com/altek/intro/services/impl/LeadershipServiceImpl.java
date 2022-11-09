package com.altek.intro.services.impl;

import com.altek.intro.dto.request.LeadershipRequestDTO;
import com.altek.intro.entites.LeadershipEntity;
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
    public List<LeadershipRequestDTO> getAllLeadership() {
        try {
            List<LeadershipRequestDTO> leadershipDTOS = new ArrayList<LeadershipRequestDTO>();
            List<LeadershipEntity> leadershipEntities = leadershipRepository.findAll();
            LeadershipRequestDTO dto = new LeadershipRequestDTO();
            if (CollectionUtils.isNotEmpty(leadershipEntities)) {
                leadershipDTOS = leadershipEntities.stream().map(item -> (LeadershipRequestDTO) leadershipMapper.convertToDTO(dto, item))
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
    public LeadershipRequestDTO Create(LeadershipRequestDTO request) {
        LeadershipEntity entity = new LeadershipEntity();
        entity = (LeadershipEntity) leadershipMapper.convertToEntity(request,entity);
        entity = leadershipRepository.save(entity);
        LeadershipRequestDTO response = modelMapper.map(entity, LeadershipRequestDTO.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipRequestDTO Delete(Long id) {
        Optional<LeadershipEntity> optional = leadershipRepository.findById(id);
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
        }
        LeadershipEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        leadershipRepository.save(entity);
        LeadershipRequestDTO response = modelMapper.map(entity, LeadershipRequestDTO.class);
        return response;
    }
}
