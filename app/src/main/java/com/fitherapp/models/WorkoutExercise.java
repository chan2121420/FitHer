package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_exercises",
        foreignKeys = {
                @ForeignKey(entity = WorkoutPlan.class, parentColumns = "id",
                        childColumns = "workoutPlanId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Exercise.class, parentColumns = "id",
                        childColumns = "exerciseId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("workoutPlanId"), @Index("exerciseId")})
public class WorkoutExercise {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int workoutPlanId;
    public int exerciseId;
    public int orderIndex;
    public int sets;
    public int reps;
    public int durationSecs;
    public int restTimeSecs;
    public boolean isBilateral;
    public String notes;
    public String modeOverride;
    public int tempoEccentric;  // seconds for lowering
    public int tempoConcentric; // seconds for lifting
    public int tempoHold;       // seconds to hold

    public WorkoutExercise() {}

    public WorkoutExercise(int workoutPlanId, int exerciseId, int orderIndex,
                           int sets, int reps, int durationSecs, int restTimeSecs,
                           boolean isBilateral, String notes) {
        this.workoutPlanId = workoutPlanId;
        this.exerciseId = exerciseId;
        this.orderIndex = orderIndex;
        this.sets = sets;
        this.reps = reps;
        this.durationSecs = durationSecs;
        this.restTimeSecs = restTimeSecs;
        this.isBilateral = isBilateral;
        this.notes = notes;
    }
}