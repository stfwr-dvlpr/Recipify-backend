package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateContent(String prompt) {
        String fullApiUrl = apiUrl + "?key=" + apiKey;

        // Construct the request body for Gemini API
        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}",
            prompt
        );
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            String rawResponse = restTemplate.postForObject(fullApiUrl, requestBody, String.class);

            // Parse the raw JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(rawResponse);

            // Navigate through the JSON to find the recipe text
            String recipeText = rootNode
                    .path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();

            return recipeText;

        } catch (Exception e) {
            // Handle potential errors, e.g., JSON parsing error or no candidates
            //logger.error("Error parsing Gemini response: {}", e.getMessage());
            return "Error: Could not parse AI response.";
        }
    }
}