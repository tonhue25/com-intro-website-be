package com.altek.intro.services;


import java.util.List;

import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import org.springframework.stereotype.Service;


@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipResponseDTO> getAllLeadership();
    LeadershipResponseDTO create(LeadershipRequestDto request);
    LeadershipResponseDTO delete(Long id);
}
