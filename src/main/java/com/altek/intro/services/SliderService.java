package com.altek.intro.services;

import java.util.List;

import com.altek.intro.dto.response.SliderResponseDTO;

public interface SliderService extends AbstractService{

    List<SliderResponseDTO> getAll();

}
