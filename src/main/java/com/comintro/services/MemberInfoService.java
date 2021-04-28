package com.comintro.services;

import com.comintro.dto.MemberInfoDTO;
import com.comintro.entities.MemberInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberInfoService extends AbstractService<MemberInfoDTO, MemberInfoEntity> {
    List<MemberInfoDTO> getListMemberInfoByType(Integer type);
}
