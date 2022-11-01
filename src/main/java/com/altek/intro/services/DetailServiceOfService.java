package com.altek.intro.services;

import com.altek.intro.dto.DetailServiceDTO;

import java.util.List;

public interface DetailServiceOfService extends AbstractService{
    List<DetailServiceDTO> getAllDetailService();

}
