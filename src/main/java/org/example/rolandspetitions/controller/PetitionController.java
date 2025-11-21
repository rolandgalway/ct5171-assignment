package org.example.rolandspetitions.controller;

import org.example.rolandspetitions.model.Petition;
import org.example.rolandspetitions.model.Signature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetitionController {

    private final List<Petition> petitions = new ArrayList<>();

    public PetitionController() {
        petitions.add(new Petition("Save the Forests", "We must protect our forests from deforestation."));
        petitions.add(new Petition("Better Public Transport", "Improve buses and trains for everyone."));
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/create")
    public String createPetition() {
        return "create";
    }

    @PostMapping("/submit")
    public String submitPetition(@RequestParam String title,
                                 @RequestParam String description,
                                 Model model) {

        petitions.add(new Petition(title, description));

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "success";
    }

    @GetMapping("/petitions")
    public String listPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "all";
    }

    @GetMapping("/petition/{index}")
    public String viewPetition(@PathVariable int index, Model model) {

        if (index < 0 || index >= petitions.size()) {
            return "notfound";
        }

        Petition petition = petitions.get(index);

        model.addAttribute("petition", petition);
        model.addAttribute("index", index);

        return "view";
    }

    @PostMapping("/petition/{index}/sign")
    public String signPetition(@PathVariable int index,
                               @RequestParam String name,
                               @RequestParam String email,
                               Model model) {

        if (index < 0 || index >= petitions.size()) {
            return "notfound";
        }

        Petition petition = petitions.get(index);
        petition.addSignature(new Signature(name, email));

        model.addAttribute("petition", petition);
        model.addAttribute("index", index);

        return "view";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @GetMapping("/search/results")
    public String searchResults(@RequestParam String keyword, Model model) {

        List<Petition> results = new ArrayList<>();

        for (Petition p : petitions) {
            if (p.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(p);
            }
        }

        model.addAttribute("results", results);
        model.addAttribute("keyword", keyword);

        return "results";
    }
}
