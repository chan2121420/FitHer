package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String category;       // GLUTES, LEGS, ABS, ARMS, BACK, CHEST, FULL_BODY, WARMUP, COOLDOWN
    public String type;           // BODYWEIGHT, WEIGHTED, BANDS, CARDIO
    public String description;
    public String instructions;
    public String targetMuscles;
    public String secondaryMuscles;
    public boolean requiresBand;
    public int defaultReps;
    public int defaultDurationSecs;
    public int defaultSets;
    public int restTimeSecs;
    public String difficulty;     // BEGINNER, INTERMEDIATE, ADVANCED
    public String tips;           // Pro coaching tips
    public String commonMistakes; // What NOT to do
    public String breathingCue;   // Breathing pattern
    public String gifResName;     // Offline drawable name for animation frames
    public String videoUrl;       // YouTube embed URL for tutorial
    public String equipmentNeeded;
    public int caloriesPerMin;    // Estimated calories burned per minute
    public boolean isBilateral;   // Needs both sides
    public String progressionTip; // How to make it harder

    public Exercise() {}

    public Exercise(String name, String category, String type, String description,
                    String instructions, String targetMuscles, String secondaryMuscles,
                    boolean requiresBand, int defaultReps, int defaultDurationSecs,
                    int defaultSets, int restTimeSecs, String difficulty,
                    String tips, String commonMistakes, String breathingCue,
                    String equipmentNeeded, int caloriesPerMin, boolean isBilateral,
                    String progressionTip) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.description = description;
        this.instructions = instructions;
        this.targetMuscles = targetMuscles;
        this.secondaryMuscles = secondaryMuscles;
        this.requiresBand = requiresBand;
        this.defaultReps = defaultReps;
        this.defaultDurationSecs = defaultDurationSecs;
        this.defaultSets = defaultSets;
        this.restTimeSecs = restTimeSecs;
        this.difficulty = difficulty;
        this.tips = tips;
        this.commonMistakes = commonMistakes;
        this.breathingCue = breathingCue;
        this.equipmentNeeded = equipmentNeeded;
        this.caloriesPerMin = caloriesPerMin;
        this.isBilateral = isBilateral;
        this.progressionTip = progressionTip;
    }
}