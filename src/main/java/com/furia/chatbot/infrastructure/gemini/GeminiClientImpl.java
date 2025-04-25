package com.furia.chatbot.infrastructure.gemini;

import com.furia.chatbot.domain.gateway.GeminiGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
@RequiredArgsConstructor
public class GeminiClientImpl implements GeminiGateway {

    @Value("${gemini.api.key}")
    private String API_KEY;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getResponse(String userMessage)
    {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-lite:generateContent?key=%s"
                .formatted(API_KEY);

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
                """.formatted(userMessage);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to call Gemini API: " + response.getStatusCode() + " " + response.getBody());
        }
    }
}
