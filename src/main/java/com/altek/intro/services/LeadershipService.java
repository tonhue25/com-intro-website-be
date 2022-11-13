package com.altek.intro.services;


import com.altek.intro.dto.request.LeadershipRequestDTO;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipResponseDTO> getAllLeadership();
    LeadershipResponseDTO Create(LeadershipRequestDTO request);
    LeadershipRequestDTO Delete(Long id);
}
