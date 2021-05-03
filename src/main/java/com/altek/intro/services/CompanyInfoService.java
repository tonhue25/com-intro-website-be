package com.altek.intro.services;

import com.altek.intro.dto.CompanyInfoViewDTO;
import com.altek.intro.dto.MenuViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyInfoService extends AbstractService {
    List<CompanyInfoViewDTO> getAllCompanyInfo() throws Exception;
    CompanyInfoViewDTO getAllCompanyInfoById(Long id) throws Exception;
}
