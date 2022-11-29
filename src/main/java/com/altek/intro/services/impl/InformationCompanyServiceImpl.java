package com.altek.intro.services.impl;

import com.altek.intro.config.GlobalConfig;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.services.InformationCompanyService;
import com.altek.intro.utils.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InformationCompanyServiceImpl extends AbstractServiceImpl implements InformationCompanyService {
    @Autowired
    GlobalConfig globalConfig;

    public BaseResponse getInformationCompany(){
        try {
            return new BaseResponse(Constant.SUCCESS, "get.information.company", globalConfig.toString());
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
        }
    }
}
