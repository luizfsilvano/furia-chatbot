package com.furia.chatbot.domain.tracker;

import com.furia.chatbot.domain.model.InteractionLog;

import java.util.List;

public interface InteractionLogger {

    void logInteraction(String message, String botResponse, boolean wasFallback);

    int getTotalMessages();

    int getFallbackUsage();

    List<InteractionLog> getLogs();

}
