package com.altek.intro.services.impl;

import com.altek.intro.dto.ClientReviewViewDTO;
import com.altek.intro.entites.ClientReviewEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ClientReviewMapper;
import com.altek.intro.repository.ClientReviewRepository;
import com.altek.intro.services.ClientReviewService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientReviewServiceImpl extends AbstractServiceImpl implements ClientReviewService {
    @Autowired
    private ClientReviewRepository clientReviewRepository;
    @Autowired
    private ClientReviewMapper clientReviewMapper;
    @Override
    public List<ClientReviewViewDTO> getAllClientReview() throws Exception {
        try {
            List<ClientReviewViewDTO> clientReviewViewDTOS = new ArrayList<ClientReviewViewDTO>();

            List<ClientReviewEntity> clientReviewEntities = clientReviewRepository.findAll();
            ClientReviewViewDTO dto = new ClientReviewViewDTO();
            if (CollectionUtils.isNotEmpty(clientReviewEntities)) {
                clientReviewViewDTOS = clientReviewEntities.stream().map(item -> (ClientReviewViewDTO) clientReviewMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return clientReviewViewDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
