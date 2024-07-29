package com.example.itau.response;

public class JwtValidationResponse {

    private boolean valid;

    public JwtValidationResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
