package com.example.itau.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {


    @GetMapping("/validate-jwt")
    public String validateJwt(@RequestParam String jwt) {

        return "Hello";
    }
}
