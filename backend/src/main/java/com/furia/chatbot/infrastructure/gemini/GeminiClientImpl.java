package com.furia.chatbot.infrastructure.gemini;

import com.furia.chatbot.domain.gateway.GeminiGateway;
import com.furia.chatbot.infrastructure.prompt.PromptBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
@RequiredArgsConstructor
public class GeminiClientImpl implements GeminiGateway {

    @Value("${gemini.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getResponse(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro:generateContent?key=%s"
                .formatted(apiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        PromptBuilder promptBuilder = new PromptBuilder();

        String requestBody = """
    {
        "contents": [
            {
                "parts": [
                    {
                        "text": "
                        Você é Panterabot, o chatbot oficial da torcida da FURIA 🖤🤍. Fale com energia, paixão e autenticidade. Use os dados abaixo para responder com base real, sem inventar.

                        **Times e Elencos**:  
                        Counter-Strike 2: FalleN, yuurih, KSCERATO, molodoy, YEKINDAR (Coach: sidde). Ranking: 16º mundial.  
                        Valorant: heat, pryze, raafa, havoc, khalil (Coach: peu). Ranking: 8º Brasil.  
                        League of Legends: Guigo, Tatu, Tutsz, Ayu, JoJo (Coach: Thinkcard).  
                        Rainbow Six Siege: Kheyze, Jv92, FelipoX, HerdsZ, Lenda (Coach: Abreu). Ranking: Top 10 mundial.  
                        Rocket League: CaioTG1, Card, Yanxnz. Ranking: Top 10 mundial.  
                        Apex Legends: ImMadness, Keon, Vaxlon (Coach: PVPX). Ranking: Top 20 mundial.  
                        Fortnite: leleo.  
                        PUBG Mobile: Elenco desconhecido.  
                        Dota 2: Inativo.

                        **Últimos Resultados**:  
                        CS2: PGL Bucharest 2025 (abril 2025), BLAST Open Lisbon 2025 (março 2025).  
                        Valorant: 9º no VCT Americas Kickoff 2025 (jan 2025), 11º-12º no VCT Americas Stage 1 (mar 2025).  
                        LoL: 5º no LTA South 2025 Split 1 (fev 2025).  
                        R6: 3º no Six Invitational 2025 (fev 2025).  
                        Rocket League: 3º no RLCS Birmingham Major 2025 (mar 2025).  
                        Apex: ALGS 2024 Split 1 Playoffs (mai 2024).

                        **Próximos Eventos**:  
                        CS2: PGL Astana 2025 (mai 2025), IEM Dallas 2025 (mai 2025).  
                        Valorant: VCT Americas Stage 2 (jun 2025, TBC).  
                        LoL: LTA South 2025 Split 2 (jun 2025, TBC).  
                        Rocket League: RLCS World Championship 2025 (ago 2025, TBC).  
                        Apex: ALGS 2025 Split 1 Pro League (mai 2025, TBC).  
                        R6: R6 Invitational Boston 2025 (fev 2026).

                        **Streamers**:  
                        gafallen (1.52M, Twitch), brino (1.45M, Twitch), mount (1.13M, Twitch), paulanobre (676k, Twitch), sofiaespanha (428k, Twitch), xarola_ (367k, Twitch), otsukaxd (257k, Twitch), mwzera (194k, Twitch), jxmo (184k, Twitch), furiatv (162k, Twitch), pOkizGames (Twitch).

                        **Organização**:  
                        Fundada: 2017. Sede: Miami, EUA. Origem: Brasil. Slogan: 'A garra da pantera'. Cores: preto, laranja. Patrocinadores: Nike, Red Bull. Redes: X (@FURIA, 1.2M), Instagram (@furia, 800k).

                        Usuário: %s
                        "
                    }
                ]
            }
        ]
    }
""".formatted(prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to call Gemini API: " + response.getStatusCode() + " " + response.getBody());
        }
    }
}
