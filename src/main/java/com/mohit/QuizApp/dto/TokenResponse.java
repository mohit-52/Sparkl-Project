package com.mohit.QuizApp.dto;

public class TokenResponse {
    private String token;

    // ✅ Manually defined No-Args Constructor
    public TokenResponse() {
    }

    // ✅ Manually defined All-Args Constructor
    public TokenResponse(String token) {
        this.token = token;
    }

    // ✅ Getter & Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
