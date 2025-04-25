package com.furia.chatbot.infrastructure.gemini;

import com.furia.chatbot.domain.gateway.GeminiGateway;
import org.springframework.stereotype.Component;

@Component
public class GeminiClientImpl implements GeminiGateway {
    @Override
    public String getResponse(String userMessage)
    {
        // TODO: Integrate with Gemini API
        return null;
    }

}
