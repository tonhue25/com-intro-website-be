package com.altek.intro.controller;

import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public ResponseEntity<MenuViewDTO> listAllMenu() {
        try {
            List<MenuViewDTO> response = menuService.getAllMenu();
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
    public ResponseEntity<MenuViewDTO> getMenuId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(menuService.getMenuById(id), HttpStatus.OK);
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