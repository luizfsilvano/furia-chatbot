package com.furia.chatbot.domain.tracker;

public interface InteractionLogger {
    void incrementMessageCount();

    void incrementFallbackUsage();

    int getTotalMessages();

    int getFallbackUsage();
}
