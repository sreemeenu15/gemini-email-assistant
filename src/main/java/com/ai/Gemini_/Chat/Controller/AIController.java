package com.ai.Gemini_.Chat.Controller;

import com.ai.Gemini_.Chat.Service.AIService;
import com.ai.Gemini_.Chat.dto.EmailRequest;
import com.ai.Gemini_.Chat.dto.EmailResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AIController {

private final AIService aiService;

public AIController(AIService aiService){
    this.aiService = aiService;
}

@PostMapping("/generate")
    public EmailResponse generate(@RequestBody EmailRequest request){

    System.out.println("API HIT at: " + java.time.LocalDateTime.now());


    String content = request.getEmailContent();
    String tone = request.getTone();

    String reply = aiService.generateReply(content, tone);
    EmailResponse response = new EmailResponse();
    response.setReply(reply);
    return response;

}

}
