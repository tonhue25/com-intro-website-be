package com.altek.intro.services;


import java.util.List;

import com.altek.intro.dto.request.LeadershipRequestDTO;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.response.LeadershipResponseDTO;


@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipResponseDTO> getAllLeadership();
    LeadershipResponseDTO create(LeadershipRequestDTO request);
    LeadershipResponseDTO delete(Long id);
}
