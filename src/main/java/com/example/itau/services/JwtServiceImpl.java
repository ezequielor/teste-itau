package com.example.itau.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.itau.utils.ValidateClaims;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService{

    @Override
    public boolean validateJwt(String token) {

//        DecodedJWT jwtDecoded = jwtDecode.jwtDecode(token);
        ValidateClaims validateClaims = new ValidateClaims();
        return validateClaims.validateClaims(token);

    }
}
