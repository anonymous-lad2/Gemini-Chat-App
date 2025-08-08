package com.happysat.Gemini_chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class QnAService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.endpoint.url}")
    private String endpointUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String askGemini(String userInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-goog-api-key", apiKey);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", userInput)
                        })
                }
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                endpointUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }
}
