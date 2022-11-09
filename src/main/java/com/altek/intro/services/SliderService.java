package com.altek.intro.services;

import com.altek.intro.dto.request.SliderRequestDTO;

import java.util.List;

public interface SliderService extends AbstractService{

    List<SliderRequestDTO> getAllSlide();

}
