package com.furia.chatbot.infrastructure.fallback;

import com.furia.chatbot.domain.repository.FallbackRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FallbackRepositoryImpl implements FallbackRepository {
    @Override
    public String getRandomFallback()
    {
        // TODO: Return fallback from memory or local storage
        return null;
    }
}
