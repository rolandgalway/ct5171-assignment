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

    private List<Petition> petitions = new ArrayList<>();

    // Preload sample petitions
    public PetitionController() {
        petitions.add(new Petition(1,"Save the Forests", "We must protect our forests from deforestation."));
        petitions.add(new Petition(2, "Better Public Transport", "Improve buses and trains for everyone."));
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

        Petition petition = new Petition(petitions.size() + 1, title, description);
        petitions.add(petition);

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "success";
    }

    @GetMapping("/petitions")
    public String listPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "petitions";
    }

    @GetMapping("/petition/{index}")
    public String viewPetition(@PathVariable int index, Model model) {
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

        Petition petition = petitions.get(index);
        petition.addSignature(new Signature(name, email));

        model.addAttribute("petition", petition);
        model.addAttribute("index", index);

        return "view";
    }
}
