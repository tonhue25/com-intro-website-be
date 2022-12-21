package com.altek.intro.service;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface LeadershipService extends AbstractService {
    BaseResponse getLeaderships(BaseRequest request);

    BaseResponse createNewLeadership(LeadershipRequestDto request);

    BaseResponse updateLeadership(LeadershipRequestDto request);

    BaseResponse deleteLeadership(LeadershipRequestDto request);
}
