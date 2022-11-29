package com.altek.intro.services;


import java.util.List;

import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import org.springframework.stereotype.Service;


@Service
public interface LeadershipService extends AbstractService{
    BaseResponse getAllLeadership(String lang);
    LeadershipResponseDto create(LeadershipRequestDto request);
    LeadershipResponseDto delete(Long id);
}
