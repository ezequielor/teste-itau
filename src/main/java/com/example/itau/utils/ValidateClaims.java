package com.example.itau.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateClaims {

    private static final Logger logger = LoggerFactory.getLogger(ValidateClaims.class);

    public boolean validateClaims(String token){

        try {

            DecodedJWT decodeJWT = JWT.decode(token);
            logger.info("token decodificado", decodeJWT);

            if (decodeJWT.getClaims().size() != 3) {
                return false;
            }

            String name = decodeJWT.getClaim("Name").asString();
            String role = decodeJWT.getClaim("Role").asString();
            String seedString = decodeJWT.getClaim("Seed").asString();
            Integer seed = Integer.parseInt(seedString);


            if (name == null || name.length() > 256 || name.chars().anyMatch(Character::isDigit)) {
                logger.info("Claim nome é maior que 256 caracteres", name);
                return false;
            }

            if (role == null || !role.matches("Admin|Member|External")) {
                logger.info("Claim role possui valor inválido", role);
                return false;
            }

            if (seed == null || !isPrime(seed)) {
                logger.info("Claim seed não é um número primo", seed);
                return false;
            }
            logger.info("Token analisado", token);
            return true;

        } catch (Exception e) {
            logger.error("Erro ao validar o token",e.getMessage());
            return false;
        }

    }

    private static boolean isPrime(int seed){

        if (seed <= 1) return false;
        if (seed == 2 || seed == 3) return true;
        if (seed % 2 == 0 || seed % 3 == 0) return false;
        for (int i = 5; i * i <= seed; i += 6) {
            if (seed % i == 0 || seed % (i + 2) == 0) return false;
        }
        return true;
    }
}
