package com.comintro.controllers;

import com.comintro.services.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/team")
public class MemberInfoController {
    @Autowired
    private MemberInfoService memberInfoService;
}
