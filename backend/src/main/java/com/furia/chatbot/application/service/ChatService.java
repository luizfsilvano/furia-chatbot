package com.furia.chatbot.application.service;

import com.furia.chatbot.domain.gateway.GeminiGateway;
import com.furia.chatbot.domain.repository.FallbackRepository;
import com.furia.chatbot.domain.tracker.InteractionLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final GeminiGateway geminiGateway;
    private final FallbackRepository fallbackRepository;
    private final InteractionLogger interactionLogger;

    public String handleUserMessage(String message)
    {
        try {
            String response = geminiGateway.getResponse(message);
            interactionLogger.logInteraction(message, response, false);
            return response;
        }
        catch(Exception e) {
            String fallback = fallbackRepository.getFallbackResponse();
            interactionLogger.logInteraction(message, fallback, true);
            return fallback;
        }
    }
}
