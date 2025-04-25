package com.furia.chatbot.infrastructure.tracker;

import com.furia.chatbot.domain.tracker.InteractionLogger;
import org.springframework.stereotype.Component;

@Component
public class InteractionLoggerImpl  implements InteractionLogger {
    @Override
    public void incrementMessageCount()
    {
        // TODO: Count messages
    }

    @Override
    public void incrementFallbackUsage()
    {
        // TODO: Count Fallbacks
    }

    @Override
    public int getTotalMessages() {
        return 0;
    }

    @Override
    public int getFallbackUsage() {
        return 0;
    }


}
