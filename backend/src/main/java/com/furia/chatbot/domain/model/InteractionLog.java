package com.furia.chatbot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InteractionLog {
    private String userMessage;
    private String botResponse;
    private LocalDateTime timestamp;
    private boolean useFallback;
}
