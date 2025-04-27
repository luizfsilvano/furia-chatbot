package com.furia.chatbot.infrastructure.tracker;

import com.furia.chatbot.domain.model.InteractionLog;
import com.furia.chatbot.domain.tracker.InteractionLogger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InteractionLoggerImpl implements InteractionLogger {

    private final List<InteractionLog> logList = new ArrayList<>();
    private int messageCount = 0;
    private int fallbackCount = 0;

    @Override
    public void logInteraction(String message, String botResponse, boolean wasFallback)
    {
        logList.add(new InteractionLog(message, botResponse, LocalDateTime.now(), wasFallback));
        messageCount++;
        if (wasFallback)
        {
            fallbackCount++;
        }
    }

    @Override
    public int getTotalMessages() {
        return getTotalMessages();
    }

    @Override
    public int getFallbackUsage() {
        return getFallbackUsage();
    }

    @Override
    public List<InteractionLog> getLogs()
    {
        return new ArrayList<>(logList);
    }


}
