package com.example.itau.controllers;

import com.example.itau.response.JwtValidationResponse;
import com.example.itau.services.JwtService;
import com.example.itau.utils.ValidateClaims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(ValidateClaims.class);

    @Operation(summary = "Validate JWT Token", description = "Receives a JWT token and validates it according to predefined rules.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT is valid"),
            @ApiResponse(responseCode = "400", description = "Invalid JWT token or claims")
    })
    @GetMapping("/validate")
    public ResponseEntity<JwtValidationResponse> validateJwt(@RequestParam String token) {
        logger.info("Recebendo token para validação");

        boolean isValid = jwtService.validateJwt(token);
        return ResponseEntity.ok(new JwtValidationResponse(isValid));
    }
}

