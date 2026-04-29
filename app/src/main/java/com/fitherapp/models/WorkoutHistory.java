package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_history")
public class WorkoutHistory {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String supabaseId;
    public int userId;
    public int workoutPlanId;
    public String workoutName;
    public long dateCompleted;
    public int durationSecs;
    public int caloriesBurned;
    public int totalReps;
    public boolean completed;
    public String bandLevel; // NONE, LIGHT, HEAVY
    public String notes;

    public WorkoutHistory() {}

    public WorkoutHistory(int userId, int workoutPlanId, String workoutName,
                          long dateCompleted, int durationSecs, int caloriesBurned, String bandLevel) {
        this.userId = userId;
        this.workoutPlanId = workoutPlanId;
        this.workoutName = workoutName;
        this.dateCompleted = dateCompleted;
        this.durationSecs = durationSecs;
        this.caloriesBurned = caloriesBurned;
        this.bandLevel = bandLevel;
        this.completed = true;
    }
}