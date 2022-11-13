package com.altek.intro.controller;

import com.altek.intro.dto.response.SliderResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.SliderService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/slider")
@Slf4j
public class SliderController {
    @Autowired
    private SliderService sliderService;

    @GetMapping
    public ResponseEntity<SliderResponseDTO> listAll() {
        try {
            List<SliderResponseDTO> response = sliderService.getAll();
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
