package com.example.itau.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.itau.utils.ValidateClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService{

    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Override
    public boolean validateJwt(String token) {

        logger.info("analisando token", token);
        ValidateClaims validateClaims = new ValidateClaims();
        return validateClaims.validateClaims(token);

    }
}
