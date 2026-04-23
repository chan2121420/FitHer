package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_exercises",
        foreignKeys = {
            @ForeignKey(entity = WorkoutDay.class, parentColumns = "id",
                        childColumns = "workoutDayId", onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Exercise.class, parentColumns = "id",
                        childColumns = "exerciseId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("workoutDayId"), @Index("exerciseId")})
public class WorkoutExercise {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int workoutDayId;
    public int exerciseId;
    public int orderIndex;
    public int sets;
    public int reps;              // 0 if timed
    public int durationSecs;      // 0 if reps-based
    public boolean isBilateral;   // true = do left AND right
    public String notes;

    public WorkoutExercise() {}

    public WorkoutExercise(int workoutDayId, int exerciseId, int orderIndex,
                            int sets, int reps, int durationSecs,
                            boolean isBilateral, String notes) {
        this.workoutDayId = workoutDayId;
        this.exerciseId = exerciseId;
        this.orderIndex = orderIndex;
        this.sets = sets;
        this.reps = reps;
        this.durationSecs = durationSecs;
        this.isBilateral = isBilateral;
        this.notes = notes;
    }
}
