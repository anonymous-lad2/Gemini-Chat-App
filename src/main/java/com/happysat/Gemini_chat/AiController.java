package com.happysat.Gemini_chat;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class AiController {

    private final QnAService qnAService;

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody Map<String, String> body) {
        String question = body.get("question");
        String response = qnAService.askGemini(question);
        return ResponseEntity.ok(response);
    }
}
