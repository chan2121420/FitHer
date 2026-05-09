package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_plans")
public class WorkoutPlan {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    public String difficulty;
    public String category;
    public int estimatedMins;
    public int estimatedCalories;
    public boolean isCustom;
    public long createdAt;
    public String week;           // e.g. "Week 1", "Week 2"
    public int daysPerWeek;
    public String focusDescription; // e.g. "Build glute strength and hip width"
    public String level;          // BEGINNER, INTERMEDIATE, ADVANCED
    public String equipment;      // NONE, BANDS, DUMBBELLS, FULL_GYM
    public int exerciseCount;

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