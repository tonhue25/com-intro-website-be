package com.altek.intro.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.response.LeadershipResponseDTO;


@Service
public interface LeadershipService extends AbstractService{
    List<LeadershipResponseDTO> getAllLeadership();
}
