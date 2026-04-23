package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_days")
public class WorkoutDay {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int dayOfWeek;      // 1=Mon, 2=Tue ... 7=Sun
    public String dayName;     // "Monday"
    public String focusTitle;  // "Glute Activation & Strength"
    public String focusType;   // "GLUTE", "HIP", "REST", "CORE"
    public int estimatedMins;
    public int estimatedCalories;
    public boolean isRestDay;
    public int weekNumber;     // For progressive overload: 1, 2, 3, 4 (cycles of 2 weeks)

    public WorkoutDay() {}

    public WorkoutDay(int dayOfWeek, String dayName, String focusTitle,
                      String focusType, int estimatedMins, int estimatedCalories,
                      boolean isRestDay) {
        this.dayOfWeek = dayOfWeek;
        this.dayName = dayName;
        this.focusTitle = focusTitle;
        this.focusType = focusType;
        this.estimatedMins = estimatedMins;
        this.estimatedCalories = estimatedCalories;
        this.isRestDay = isRestDay;
        this.weekNumber = 1;
    }
}
