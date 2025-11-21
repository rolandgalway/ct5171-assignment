package org.example.rolandspetitions.model;

import java.util.ArrayList;
import java.util.List;

public class Petition {

    private int id;
    private String title;
    private String description;
    private List<Signature> signatures = new ArrayList<>();

    // Constructor with ID (required by controller)
    public Petition(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Optional no-arg constructor (good practice)
    public Petition() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void addSignature(Signature signature) {
        signatures.add(signature);
    }
}
