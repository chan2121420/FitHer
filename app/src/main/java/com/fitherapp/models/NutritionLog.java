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

    public NutritionLog(long dateRecorded, float proteinG, float caloriesTotal,
                        float waterMl, boolean massGainerTaken, float massGainerServings) {
        this.dateRecorded = dateRecorded;
        this.proteinG = proteinG;
        this.caloriesTotal = caloriesTotal;
        this.waterMl = waterMl;
        this.massGainerTaken = massGainerTaken;
        this.massGainerServings = massGainerServings;
    }
}
