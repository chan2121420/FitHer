package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String category;       // GLUTES, LEGS, ABS, ARMS, BACK, CHEST, FULL_BODY
    public String type;           // BODYWEIGHT, WEIGHTED, BANDS
    public String description;
    public String instructions;
    public String targetMuscles;
    public boolean requiresBand;
    public int defaultReps;
    public int defaultDurationSecs;
    public int defaultSets;
    public int restTimeSecs;
    public String difficulty;     // BEGINNER, INTERMEDIATE, ADVANCED
    public String imageUrl;
    public String gifResName;

    public Exercise() {}

    public Exercise(String name, String category, String type, String description,
                    String instructions, String targetMuscles, boolean requiresBand,
                    int defaultReps, int defaultDurationSecs, int defaultSets,
                    int restTimeSecs, String difficulty) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.description = description;
        this.instructions = instructions;
        this.targetMuscles = targetMuscles;
        this.requiresBand = requiresBand;
        this.defaultReps = defaultReps;
        this.defaultDurationSecs = defaultDurationSecs;
        this.defaultSets = defaultSets;
        this.restTimeSecs = restTimeSecs;
        this.difficulty = difficulty;
    }
}