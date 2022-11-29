package com.altek.intro.service.impl;

import com.altek.intro.config.GlobalConfig;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.InformationCompanyService;
import com.altek.intro.util.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InformationCompanyServiceImpl extends AbstractServiceImpl implements InformationCompanyService {
    @Autowired
    GlobalConfig globalConfig;

    public BaseResponse getInformationCompany(){
        try {
            return new BaseResponse(Constant.SUCCESS, "get.information.company", globalConfig);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
        }
    }
}
