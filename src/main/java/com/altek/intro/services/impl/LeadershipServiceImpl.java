package com.altek.intro.services.impl;


import com.altek.intro.dto.request.LeadershipRequestDTO;
import com.altek.intro.dto.response.LeadershipResponseDTO;
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
            List<LeadershipResponseDTO> leadershipDTOS = new ArrayList<>();
            List<LeadershipEntity> leadershipEntities = leadershipRepository.findAll();
            LeadershipResponseDTO dto = new LeadershipResponseDTO();
            if (CollectionUtils.isNotEmpty(leadershipEntities)) {
                List<LeadershipResponseDTO> list = new ArrayList<>();
                for (LeadershipEntity item : leadershipEntities) {
                    Object o = leadershipMapper.convertToDTO(item, dto);
                    list.add((LeadershipResponseDTO) o);
                }
                leadershipDTOS = list;
            }
            return leadershipDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipResponseDTO Create(LeadershipRequestDTO request) {
        LeadershipEntity entity = new LeadershipEntity();
        entity = (LeadershipEntity) leadershipMapper.convertToEntity(request, entity);
        entity = leadershipRepository.save(entity);
        LeadershipResponseDTO response = modelMapper.map(entity, LeadershipResponseDTO.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipResponseDTO Delete(Long id) {
        Optional<LeadershipEntity> optional = leadershipRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("menu.not.found.with.id:%s", id));
        }
        LeadershipEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        leadershipRepository.save(entity);
        LeadershipResponseDTO response = modelMapper.map(entity, LeadershipResponseDTO.class);
        return response;
    }
}
