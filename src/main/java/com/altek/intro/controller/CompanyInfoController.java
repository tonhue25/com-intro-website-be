package com.altek.intro.controller;

import com.altek.intro.dto.CompanyInfoViewDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.CompanyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyInfo")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompanyInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInfoController.class);

    @Autowired
    CompanyInfoService companyInfoService;

    @GetMapping("/")
    public ResponseEntity<CompanyInfoViewDTO> listAllCompanyInfo() {
        try {
            List<CompanyInfoViewDTO> response = companyInfoService.getAllCompanyInfo();
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
    @GetMapping("/{id}")
    public ResponseEntity<CompanyInfoViewDTO> getCompanyInfoId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(companyInfoService.getAllCompanyInfoById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
