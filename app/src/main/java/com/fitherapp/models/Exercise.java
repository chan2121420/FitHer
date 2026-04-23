package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String category;        // GLUTE, HIP, CORE, WARMUP, COOLDOWN
    public String description;
    public String instructions;
    public String targetMuscles;
    public boolean requiresBand;
    public int defaultReps;        // 0 if timed
    public int defaultDurationSecs; // 0 if reps-based
    public String difficulty;      // BEGINNER, INTERMEDIATE, ADVANCED
    public String gifResName;      // drawable resource name for animation

    public Exercise() {}

    public Exercise(String name, String category, String description,
                    String instructions, String targetMuscles,
                    boolean requiresBand, int defaultReps, int defaultDurationSecs,
                    String difficulty) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.instructions = instructions;
        this.targetMuscles = targetMuscles;
        this.requiresBand = requiresBand;
        this.defaultReps = defaultReps;
        this.defaultDurationSecs = defaultDurationSecs;
        this.difficulty = difficulty;
    }
}
