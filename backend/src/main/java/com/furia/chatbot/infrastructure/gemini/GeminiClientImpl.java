package com.furia.chatbot.infrastructure.gemini;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furia.chatbot.domain.gateway.GeminiGateway;
import com.furia.chatbot.infrastructure.prompt.PromptBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
@RequiredArgsConstructor
public class GeminiClientImpl implements GeminiGateway {

    @Value("${gemini.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String getResponse(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-lite:generateContent?key=%s"
                .formatted(apiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = """
    {
        "contents": [
            {
                "parts": [
                    {
                        "text": "%s"
                    }
                ]
            }
        ]
    }
""".formatted(prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                String generatedText = root
                        .path("candidates").get(0)
                        .path("content")
                        .path("parts").get(0)
                        .path("text")
                        .asText();

                return generatedText;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse Gemini response: " + e.getMessage(), e);
                }
        } else {
            throw new RuntimeException("Failed to call Gemini API: " + response.getStatusCode() + " " + response.getBody());
        }
    }
}
