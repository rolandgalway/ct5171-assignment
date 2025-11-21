package org.example.rolandspetitions.service;

import org.example.rolandspetitions.model.Petition;
import org.example.rolandspetitions.model.Signature;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionService {

    private List<Petition> petitions = new ArrayList<>();

    public PetitionService() {
        // Initial seed data
        petitions.add(new Petition(1, "Save the Rainforest", "We must protect the Amazon rainforest."));
        petitions.add(new Petition(2, "Ban Single-Use Plastics", "Reduce plastic waste by banning single-use plastics."));
        petitions.add(new Petition(3, "Free Public Transport", "Make public transportation free for all citizens."));
    }

    public List<Petition> getAllPetitions() {
        return petitions;
    }

    public Petition getPetitionById(int id) {
        return petitions.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addPetition(String title, String description) {
        int newId = petitions.size() + 1;
        petitions.add(new Petition(newId, title, description));
    }

    public void signPetition(int petitionId, String name, String email) {
        Petition petition = getPetitionById(petitionId);
        if (petition != null) {
            petition.addSignature(new Signature(name, email));
        }
    }

    public List<Petition> search(String query) {
        String lower = query.toLowerCase();
        List<Petition> results = new ArrayList<>();

        for (Petition p : petitions) {
            if (p.getTitle().toLowerCase().contains(lower) ||
                p.getDescription().toLowerCase().contains(lower)) {
                results.add(p);
            }
        }
        return results;
    }
}
