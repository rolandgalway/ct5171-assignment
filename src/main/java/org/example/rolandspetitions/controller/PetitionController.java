package org.example.rolandspetitions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PetitionController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/create")
    public String createPetitionPage() {
        return "create";
    }

    @PostMapping("/submit")
    public String submitPetition(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "success";
    }
}
