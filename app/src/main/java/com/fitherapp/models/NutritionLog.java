package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nutrition_logs")
public class NutritionLog {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public long dateRecorded;
    public float proteinG;
    public float caloriesTotal;
    public float waterMl;
    public boolean massGainerTaken;
    public float massGainerServings;
    public String mealNotes;

    public NutritionLog() {}
}