package com.fitherapp.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ExerciseWithDetails {

    @Embedded
    public WorkoutExercise workoutExercise;

    @Relation(parentColumn = "exerciseId", entityColumn = "id")
    public Exercise exercise;

    public String getDisplayReps() {
        if (workoutExercise.durationSecs > 0) {
            return workoutExercise.durationSecs + "s";
        }
        if (workoutExercise.isBilateral) {
            return "x" + workoutExercise.reps + " each side";
        }
        return "x" + workoutExercise.reps;
    }

    public String getSetsSummary() {
        return workoutExercise.sets + " sets";
    }
}
