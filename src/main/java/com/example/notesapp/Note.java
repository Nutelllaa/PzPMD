package com.example.notesapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Note {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String location;
    private boolean completed;
    private List<SubNote> subNotes;

    public Note(String title, String description, String imageUrl, String location, boolean completed, List<SubNote> subNotes) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.location = location;
        this.completed = completed;
        this.subNotes = subNotes == null ? new ArrayList<>() : subNotes;
    }

    public int countCompletedSubNotes() {
        int count = 0;
        for (SubNote subNote : subNotes) {
            if (subNote.isCompleted()) count++;
        }
        return count;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getLocation() { return location; }
    public boolean isCompleted() { return completed; }
    public List<SubNote> getSubNotes() { return subNotes; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
