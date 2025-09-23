package com.example.demo.controller;

import com.example.demo.dto.PromptRequest;
import com.example.demo.dto.GeminiResponseDto;
import com.example.demo.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Base route for this controller
public class GenerationController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/generate")
    public ResponseEntity<GeminiResponseDto> generateContent(@RequestBody PromptRequest promptRequest) {
    	try {
            String recipeText = geminiService.generateContent(promptRequest.getPrompt());
            // Wrap the clean text in our DTO
            return ResponseEntity.ok(new GeminiResponseDto(recipeText));
        } catch (Exception e) {
            e.printStackTrace();
            // You can also create an error DTO for consistency
            return ResponseEntity.status(500).body(new GeminiResponseDto("Error generating content."));
        }
    }
}