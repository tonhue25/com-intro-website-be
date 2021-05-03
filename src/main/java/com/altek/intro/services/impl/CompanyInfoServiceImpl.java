package com.altek.intro.services.impl;

import com.altek.intro.dto.CompanyInfoViewDTO;
import com.altek.intro.dto.CompanyInfoViewDTO;
import com.altek.intro.entites.CompanyInfoEntity;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.CompanyInfoMapper;
import com.altek.intro.repository.CompanyInfoRepository;
import com.altek.intro.services.CompanyInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyInfoServiceImpl extends AbstractServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoRepository companyInfoRepository;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Override
    public List<CompanyInfoViewDTO> getAllCompanyInfo() throws Exception {
        try {
            List<CompanyInfoViewDTO> companyInfoViewDTOS = new ArrayList<CompanyInfoViewDTO>();

            List<CompanyInfoEntity> companyInfoEntities = companyInfoRepository.findAll();
            CompanyInfoViewDTO dto = new CompanyInfoViewDTO();
            if (CollectionUtils.isNotEmpty(companyInfoEntities)) {
                companyInfoViewDTOS = companyInfoEntities.stream().map(item -> (CompanyInfoViewDTO) companyInfoMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return companyInfoViewDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public CompanyInfoViewDTO getAllCompanyInfoById(Long id) throws Exception {
        try {
            CompanyInfoViewDTO CompanyInfoViewDTO = new CompanyInfoViewDTO();
            CompanyInfoEntity companyInfoEntity = companyInfoRepository.findById(id).get();
            CompanyInfoViewDTO = (CompanyInfoViewDTO) companyInfoMapper.convertToDTO(CompanyInfoViewDTO,companyInfoEntity);
            return CompanyInfoViewDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
