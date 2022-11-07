package com.altek.intro.controller;

import com.altek.intro.services.PageDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagedetail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageDetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageDetailController.class);

    @Autowired
    private PageDetailService pageDetailService;

}
