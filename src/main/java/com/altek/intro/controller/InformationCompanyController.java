package com.altek.intro.controller;

import com.altek.intro.config.GlobalConfig;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.InformationCompanyService;
import com.altek.intro.services.impl.InformationCompanyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/information-company")
@Slf4j
public class InformationCompanyController {
    @Autowired
    InformationCompanyService informationCompanyService;

    @GetMapping
    public ResponseEntity<BaseResponse> informationCompany() {
        try {
            BaseResponse response = informationCompanyService.getInformationCompany();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}