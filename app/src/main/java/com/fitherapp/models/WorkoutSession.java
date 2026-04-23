package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_sessions")
public class WorkoutSession {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int workoutDayId;
    public long dateCompleted;    // timestamp millis
    public int durationSecs;      // actual time taken
    public int caloriesBurned;
    public int totalRepsCompleted;
    public String bandLevel;      // NONE, LIGHT, HEAVY
    public String notes;
    public boolean completed;

    public WorkoutSession() {}

    public WorkoutSession(int workoutDayId, long dateCompleted, int durationSecs,
                          int caloriesBurned, String bandLevel) {
        this.workoutDayId = workoutDayId;
        this.dateCompleted = dateCompleted;
        this.durationSecs = durationSecs;
        this.caloriesBurned = caloriesBurned;
        this.bandLevel = bandLevel;
        this.completed = true;
    }
}
