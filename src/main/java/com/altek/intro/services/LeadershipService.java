package com.altek.intro.services;


import com.altek.intro.dto.request.LeadershipRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipRequestDTO> getAllLeadership();
    LeadershipRequestDTO Create(LeadershipRequestDTO request);
    LeadershipRequestDTO Delete(Long id);
}
