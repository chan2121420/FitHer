package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String supabaseId;
    public String email;
    public String name;
    public String fitnessLevel; // BEGINNER, INTERMEDIATE, ADVANCED
    public String goal;         // GLUTES, LEGS, FULL_BODY, WEIGHT_LOSS, STRENGTH
    public long createdAt;
    public float weightKg;
    public String preferredMode; // BODYWEIGHT, BANDS, WEIGHTED

    public User() {}

    public User(String email, String name, String fitnessLevel, String goal) {
        this.email = email;
        this.name = name;
        this.fitnessLevel = fitnessLevel;
        this.goal = goal;
        this.createdAt = System.currentTimeMillis();
        this.preferredMode = "BODYWEIGHT";
    }
}