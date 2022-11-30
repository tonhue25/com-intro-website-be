package com.altek.intro.service;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import org.springframework.stereotype.Service;


@Service
public interface LeadershipService extends AbstractService{
    BaseResponse getListLeadership(BaseRequest request);
    LeadershipResponseDto create(LeadershipRequestDto request);
    LeadershipResponseDto delete(Long id);
}
