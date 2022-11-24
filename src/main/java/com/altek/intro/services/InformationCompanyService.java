package com.altek.intro.services;


import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;


@Service
public interface InformationCompanyService extends AbstractService{
     BaseResponse getInformationCompany();
}
