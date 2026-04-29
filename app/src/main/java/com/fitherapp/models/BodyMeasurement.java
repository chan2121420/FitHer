package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "body_measurements")
public class BodyMeasurement {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public long dateRecorded;
    public float weightKg;
    public float hipsCm;
    public float waistCm;
    public float glutesCm;
    public float thighLeftCm;
    public float thighRightCm;
    public float bustCm;
    public String notes;

    public BodyMeasurement() {}
}