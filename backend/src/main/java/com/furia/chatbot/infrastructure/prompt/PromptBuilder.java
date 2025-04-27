package com.furia.chatbot.infrastructure.prompt;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public String buildPromptWithHistory(String furiaContext, List<String> conversationHistory, String userMessage) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Contexto sobre a FURIA:\n");
        prompt.append(furiaContext);
        prompt.append("\n\nHistórico da conversa:\n");

        for (String entry : conversationHistory) {
            prompt.append(entry).append("\n");
        }

        prompt.append("\nNova pergunta:\n");
        prompt.append("Usuário: ").append(userMessage);

        return prompt.toString();
    }
}