package com.altek.intro.services;

import com.altek.intro.dto.HistoryDTO;

import java.util.List;

public interface HistoryService extends AbstractService{
    List<HistoryDTO> getAllHistory();
}
