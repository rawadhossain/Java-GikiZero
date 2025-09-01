package io.github.rawadhossain.GikiZero.controller;

import io.github.rawadhossain.GikiZero.model.AiTip;
import io.github.rawadhossain.GikiZero.model.User;
import io.github.rawadhossain.GikiZero.service.AiTipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aitip")
public class AiTipController {
    private final AiTipService aiTipService;

    public AiTipController(AiTipService aiTipService) {
        this.aiTipService = aiTipService;
    }

    @PostMapping
    public ResponseEntity<List<AiTip>> createTips(@RequestBody Map<String, Object> request) {
        
        String userId = (String) request.get("userId"); // in Next.js this came from session
        int totalScore = (int) request.get("totalScore");
        String impactCategory = (String) request.get("impactCategory");

       
        User user = new User();
        user.setId(userId);

        List<AiTip> tips = aiTipService.generateTips(user, request.toString(), totalScore, impactCategory);

        return ResponseEntity.ok(tips);
    }
}
