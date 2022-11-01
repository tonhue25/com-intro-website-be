package com.altek.intro.controller;

import com.altek.intro.dto.DetailServiceDTO;
import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.DetailServiceOfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detailService")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DetailServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailServiceController.class);

    @Autowired
    private DetailServiceOfService detailServiceOfService;

    @GetMapping
    public ResponseEntity<DetailServiceDTO> listAllDetailService() {
        try {
            List<DetailServiceDTO> response = detailServiceOfService.getAllDetailService();
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
