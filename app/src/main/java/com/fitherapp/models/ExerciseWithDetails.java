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
            return "×" + workoutExercise.reps + " each";
        }
        return "×" + workoutExercise.reps;
    }

    public String getSetsSummary() {
        return workoutExercise.sets + " sets";
    }

    public String getTempoDisplay() {
        if (workoutExercise.tempoEccentric > 0) {
            return workoutExercise.tempoConcentric + "-" + workoutExercise.tempoHold
                    + "-" + workoutExercise.tempoEccentric + " tempo";
        }
        return null;
    }
}