package com.altek.intro.services;

import com.altek.intro.dto.ClientReviewViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientReviewService extends AbstractService {
    List<ClientReviewViewDTO> getAllClientReview() throws Exception;
}
