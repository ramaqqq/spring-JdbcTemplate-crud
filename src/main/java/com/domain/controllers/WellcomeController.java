package com.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wellcome")
public class WellcomeController {

    @GetMapping
    public String Hello() {
        return "Hello";
    }

}
