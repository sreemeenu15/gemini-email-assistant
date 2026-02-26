package com.ai.Gemini_.Chat.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;


@Service
public class AIService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @PostConstruct
    public void checkEnv() {
        System.out.println("Gemini URL: " + geminiApiUrl);

    }

    private final WebClient webClient;

    public AIService(WebClient webClient) {
        this.webClient = webClient;
    }

    private static final Map<String, String> TONE_MAP = Map.of(
            "Professional", "Respond formally, clearly, and respectfully. Maintain workplace appropriateness.",
            "Friendly", "Respond warmly, casually, and naturally like talking to a friend.",
            "Apologetic", "Respond politely and express sincere regret if appropriate.",
            "Romantic", "Respond playfully, warmly, and affectionately.",
            "Confident", "Respond clearly and assertively."
    );

    public String generateReply(String content, String tone) {

        String finalPrompt;

        if (tone == null || tone.isBlank()) {

            finalPrompt =
                    "You are an intelligent email assistant.\n\n" +
                            "Analyze the emotional tone and context of the incoming email.\n" +
                            "Respond appropriately. If romantic, reply warmly. " +
                            "If professional, reply formally. " +
                            "If apologetic, reply gently.\n\n" +
                            "Incoming Email:\n" + content;

        } else {

            String toneInstruction =
                    TONE_MAP.getOrDefault(tone, TONE_MAP.get("Professional"));

            finalPrompt =
                    "You are an intelligent email assistant.\n\n"
                            + toneInstruction + "\n\n"
                            + "Incoming Email:\n" + content;
        }

        System.out.println("Tone received: " + tone);


        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "role", "user",
                                "parts", List.of(
                                        Map.of("text", finalPrompt)
                                )
                        )
                )
        );

        try {

            Map response = webClient.post()
                    .uri(geminiApiUrl + "?key=" + geminiApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response == null) {
                return "No response from Gemini.";
            }

            List candidates = (List) response.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                return "No response generated.";
            }

            Map firstCandidate = (Map) candidates.get(0);
            Map contentMap = (Map) firstCandidate.get("content");
            List parts = (List) contentMap.get("parts");

            if (parts == null || parts.isEmpty()) {
                return "Empty AI response.";
            }

            Map firstPart = (Map) parts.get(0);
            return (String) firstPart.get("text");

        } catch (Exception e) {
            e.printStackTrace();
            return "AI service error. Please try again.";
        }
    }


    }




