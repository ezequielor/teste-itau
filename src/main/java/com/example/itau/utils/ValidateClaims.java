package com.example.itau.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class ValidateClaims {

    public boolean validateClaims(String token){

        try {

            DecodedJWT decodeJWT = JWT.decode(token);

            if (decodeJWT.getClaims().size() != 3) {
                return false;
            }

            String name = decodeJWT.getClaim("Name").asString();
            String role = decodeJWT.getClaim("Role").asString();
            String seedString = decodeJWT.getClaim("Seed").asString();
            Integer seed = Integer.parseInt(seedString);


            if (name == null || name.length() > 256 || name.chars().anyMatch(Character::isDigit)) {
                return false;
            }

            if (role == null || !role.matches("Admin|Member|External")) {
                return false;
            }

            if (seed == null || !isPrime(seed)) {
                return false;
            }

            return true;

        } catch (Exception e) {
            System.err.println("Erro ao validar o token: " + e.getMessage());
            e.printStackTrace();
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
