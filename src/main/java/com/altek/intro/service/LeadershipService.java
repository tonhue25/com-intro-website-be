package com.altek.intro.service;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface LeadershipService extends AbstractService{
    BaseResponse getListLeadership(BaseRequest request);

}
