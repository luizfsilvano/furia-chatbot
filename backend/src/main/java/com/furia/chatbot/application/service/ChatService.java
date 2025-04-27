package com.furia.chatbot.application.service;

import com.furia.chatbot.domain.gateway.GeminiGateway;
import com.furia.chatbot.domain.repository.FallbackRepository;
import com.furia.chatbot.domain.tracker.InteractionLogger;
import com.furia.chatbot.infrastructure.prompt.PromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final GeminiGateway geminiGateway;
    private final FallbackRepository fallbackRepository;
    private final InteractionLogger interactionLogger;
    private final List<String> conversationHistory = new ArrayList<>();
    private final PromptBuilder promptBuilder;

    private final String furiaContext = """
PanteraBot responde só sobre a FURIA, com respeito. Cores: Preto e Branco. Não se cumprimente.
""";



    public String handleUserMessage(String message)
    {
        try {
            String prompt = promptBuilder.buildPromptWithHistory(furiaContext, conversationHistory, message);

            String response = geminiGateway.getResponse(prompt);

            conversationHistory.add("Usuário: " + message);
            conversationHistory.add("Panterabot: " + response);

            if (conversationHistory.size() > 6) {
                conversationHistory.remove(0);
                conversationHistory.remove(0);
            }

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
