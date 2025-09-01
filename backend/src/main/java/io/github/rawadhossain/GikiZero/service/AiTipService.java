package io.github.rawadhossain.GikiZero.service;

import io.github.rawadhossain.GikiZero.model.AiTip;
import io.github.rawadhossain.GikiZero.model.User;
import io.github.rawadhossain.GikiZero.repository.AiTipRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiTipService {
    private final AiTipRepository aiTipRepository;

    public AiTipService(AiTipRepository aiTipRepository) {
        this.aiTipRepository = aiTipRepository;
    }

    public List<AiTip> generateTips(User user, String submissionData, int totalScore, String impactCategory) {
        List<AiTip> tips = new ArrayList<>();

       
        if ("transportation".equalsIgnoreCase(impactCategory)) {
            AiTip tip = new AiTip();
            tip.setTitle("Switch to Electric or Hybrid Vehicle");
            tip.setDescription("Consider upgrading to an EV or hybrid for your next car.");
            tip.setCategory("Transportation");
            tip.setImpact("High");
            tip.setReasoning("Transportation is one of the largest emission sources.");
            tip.setUser(user);
            tips.add(tip);
        } else if ("energy".equalsIgnoreCase(impactCategory)) {
            AiTip tip = new AiTip();
            tip.setTitle("Switch to Renewable Energy");
            tip.setDescription("Install solar panels or choose a renewable energy plan.");
            tip.setCategory("Energy");
            tip.setImpact("High");
            tip.setReasoning("Energy consumption is a major source of emissions.");
            tip.setUser(user);
            tips.add(tip);
        }

        // 1. Delete old tips
        aiTipRepository.deleteByUserId(user.getId());

        // 2. Save new tips
        return aiTipRepository.saveAll(tips);
    }

    public List<AiTip> getTipsForUser(String userId) {
        return aiTipRepository.findByUserId(userId);
    }
}
