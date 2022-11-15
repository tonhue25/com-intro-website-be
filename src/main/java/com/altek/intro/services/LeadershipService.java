package com.altek.intro.services;


import java.util.List;

import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.LeadershipResponseDto;
import org.springframework.stereotype.Service;


@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipResponseDto> getAllLeadership();
    LeadershipResponseDto create(LeadershipRequestDto request);
    LeadershipResponseDto delete(Long id);
}
