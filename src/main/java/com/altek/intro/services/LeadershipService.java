package com.altek.intro.services;


import com.altek.intro.dto.LeadershipDTO;
import com.altek.intro.dto.RecruitmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipDTO> getAllLeadership();
}
