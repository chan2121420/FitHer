package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_plans")
public class WorkoutPlan {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String supabaseId;
    public int userId;
    public String name;
    public String description;
    public String difficulty;     // BEGINNER, INTERMEDIATE, ADVANCED
    public String category;       // GLUTES, LEGS, FULL_BODY, etc.
    public int estimatedMins;
    public int estimatedCalories;
    public boolean isCustom;
    public long createdAt;
    public String exerciseIdsJson; // JSON array of exercise IDs + config

    public WorkoutPlan() {}

    public WorkoutPlan(String name, String description, String difficulty,
                       String category, int estimatedMins, int estimatedCalories) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.category = category;
        this.estimatedMins = estimatedMins;
        this.estimatedCalories = estimatedCalories;
        this.isCustom = false;
        this.createdAt = System.currentTimeMillis();
    }
}