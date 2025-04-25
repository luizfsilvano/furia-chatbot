package com.furia.chatbot.domain.tracker;

public interface InteractionLogger {
    void incrementMessageCount();

    void incrementFallbackUsage();

    void logInteraction(String message, String botResponse, boolean wasFallback);

    int getTotalMessages();

    int getFallbackUsage();
}
