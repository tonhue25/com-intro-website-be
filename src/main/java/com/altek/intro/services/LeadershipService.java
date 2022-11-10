package com.altek.intro.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.LeadershipDTO;

@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipDTO> getAllLeadership();
}
