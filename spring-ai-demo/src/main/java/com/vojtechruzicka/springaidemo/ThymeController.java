package com.vojtechruzicka.springaidemo;

import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeController {

    private final ChatService chatService;
    private final ImageService imageService;

    public ThymeController(ChatService chatService, ImageService imageService) {
        this.chatService = chatService;
        this.imageService = imageService;
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/ask-ai")
    public String queryAi(@RequestParam String prompt, Model model) {
        String aiResponse = chatService.queryAi(prompt);
        model.addAttribute("aiResponse", aiResponse);
        return "index";
    }

    @PostMapping("/city-guide")
    public String getCityGuide(@RequestParam String city, @RequestParam String interest, Model model) {
        String cityGuideResponse = chatService.getCityGuide(city, interest);
        model.addAttribute("guideResponse", cityGuideResponse);
        return "index";
    }

    @PostMapping("/generate-image")
    public String generateAiImage(@RequestParam String imagePrompt, Model model) {
        ImageResponse imageResponse = imageService.generateImage(imagePrompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        model.addAttribute("imageUrl", imageUrl);
        return "index";
    }
}
