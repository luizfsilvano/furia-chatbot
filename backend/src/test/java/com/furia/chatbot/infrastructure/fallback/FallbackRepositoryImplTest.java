package com.furia.chatbot.infrastructure.fallback;

import com.furia.chatbot.infrastructure.fallback.FallbackRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.furia.chatbot.infrastructure.fallback.FallbackRepositoryImpl.FALLBACK_MESSAGES;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FallbackRepositoryImplTest {

    private FallbackRepositoryImpl fallbackRepository;

    @BeforeEach
    void setUp()
    {
        fallbackRepository = new FallbackRepositoryImpl();
    }

    @Test
    void shouldReturnNonNullFallbackMessage()
    {
        String fallback = fallbackRepository.getFallbackResponse();
        assertNotNull(fallback);
    }

    @Test
    void shouldReturnedMessageFromPredefinedList()
    {
        String fallback = fallbackRepository.getFallbackResponse();
        assertTrue(FALLBACK_MESSAGES.contains(fallback));
    }

}
