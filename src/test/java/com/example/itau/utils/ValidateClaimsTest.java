package com.example.itau.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidateClaimsTest {

    private ValidateClaims validateClaims;
    private Logger logger;

    @BeforeEach
    void setUp() {
        validateClaims = new ValidateClaims();
        logger = LoggerFactory.getLogger(ValidateClaims.class);
    }

    @Test
    void testValidToken() {
        String token = JWT.create()
                .withClaim("Name", "John Doe")
                .withClaim("Role", "Admin")
                .withClaim("Seed", "7")
                .sign(Algorithm.HMAC256("secret"));

        assertTrue(validateClaims.validateClaims(token));
    }

    @Test
    void testTokenWithInvalidNumberOfClaims() {
        String token = JWT.create()
                .withClaim("Name", "John Doe")
                .withClaim("Role", "Admin")
                .sign(Algorithm.HMAC256("secret"));

        assertFalse(validateClaims.validateClaims(token));
    }

    @Test
    void testTokenWithInvalidName() {
        String token = JWT.create()
                .withClaim("Name", "John123") // Nome inválido com dígitos
                .withClaim("Role", "Admin")
                .withClaim("Seed", "7")
                .sign(Algorithm.HMAC256("secret"));

        assertFalse(validateClaims.validateClaims(token));
    }

    @Test
    void testTokenWithInvalidRole() {
        String token = JWT.create()
                .withClaim("Name", "John Doe")
                .withClaim("Role", "Manager") // Papel inválido
                .withClaim("Seed", "7")
                .sign(Algorithm.HMAC256("secret"));

        assertFalse(validateClaims.validateClaims(token));
    }

    @Test
    void testTokenWithNonPrimeSeed() {
        String token = JWT.create()
                .withClaim("Name", "John Doe")
                .withClaim("Role", "Admin")
                .withClaim("Seed", "4") // Seed não é primo
                .sign(Algorithm.HMAC256("secret"));

        assertFalse(validateClaims.validateClaims(token));
    }

    @Test
    void testTokenDecodingException() {
        // Teste para um token malformado ou com erro ao decodificar
        String malformedToken = "malformed.token.string";

        assertFalse(validateClaims.validateClaims(malformedToken));
    }
}

