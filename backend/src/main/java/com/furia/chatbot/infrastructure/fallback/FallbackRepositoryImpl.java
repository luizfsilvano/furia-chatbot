package com.furia.chatbot.infrastructure.fallback;

import com.furia.chatbot.domain.repository.FallbackRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
public class FallbackRepositoryImpl implements FallbackRepository {

    static final List<String> FALLBACK_MESSAGES = Arrays.asList(
            "Nossa IA pinou o servidor e tomou gap! Já já ela respawna, okay?",
            "Opa, nossa IA deu um HS no servidor e tá offline! 😅 Enquanto ela respawna, aqui vai uma resposta direto do nosso QG da FURIA!",
            "Parece que nossa IA tá laggando mais que servidor lotado! 😜 Fica tranquilo, a FURIA nunca te deixa na mão, aqui vai uma resposta na raça!",
            "Nossa IA foi pro bootcamp e perdeu o sinal! 🖥️ Mas a FURIA sempre joga no clutch, então aqui vai uma resposta direto do coração da torcida!",
            "A IA deu um AFK inesperado! 😴 Enquanto ela volta pro game, a FURIA te entrega uma resposta com a garra da pantera!",
            "Nossa IA tentou um flick e errou o alvo! 🎯 Calma, a FURIA tá no controle e te manda uma resposta com aquele fogo da torcida!"
    );

    @Override
    public String getFallbackResponse()
    {
        Random random = new Random();

        return FALLBACK_MESSAGES.get(random.nextInt(FALLBACK_MESSAGES.size()));
    }
}
