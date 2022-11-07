package com.altek.intro.services;


import com.altek.intro.dto.MenuDTO;
import com.altek.intro.dto.RecruitmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruitmentService extends AbstractService{
    List<RecruitmentDTO> getAllRecruitment();
}
