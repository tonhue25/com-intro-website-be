package com.altek.intro.services;


import com.altek.intro.dto.request.RecruitmentRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruitmentService extends AbstractService{
    List<RecruitmentRequestDTO> getAllRecruitment();
    RecruitmentRequestDTO Create(RecruitmentRequestDTO request);
}
