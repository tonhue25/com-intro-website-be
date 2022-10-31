package com.altek.intro.services;

import com.altek.intro.dto.SliderDTO;

import java.util.List;

public interface SliderService extends AbstractService{

    List<SliderDTO> getAllSlide();

}
