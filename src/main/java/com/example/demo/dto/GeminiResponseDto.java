package com.example.demo.dto;

public class GeminiResponseDto {
    private String generatedText;

    public GeminiResponseDto(String generatedText) {
        this.generatedText = generatedText;
    }

    // Getters and Setters
    public String getGeneratedText() {
        return generatedText;
    }

    public void setGeneratedText(String generatedText) {
        this.generatedText = generatedText;
    }
}
