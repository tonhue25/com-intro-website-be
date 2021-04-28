package com.comintro.services.impl;

import com.comintro.dto.MemberInfoDTO;
import com.comintro.entities.MemberInfoEntity;
import com.comintro.repository.MemberInfoRepository;
import com.comintro.services.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberInfoServiceImpl extends AbstractServiceImpl<MemberInfoDTO, MemberInfoEntity> implements MemberInfoService {
    @Autowired
    private MemberInfoRepository memberInfoRepository;

    @Override
    public List<MemberInfoDTO> getListMemberInfoByType(Integer type) {
        return null;
    }
}
