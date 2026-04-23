package com.fitherapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "body_measurements")
public class BodyMeasurement {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public long dateRecorded;   // timestamp millis
    public float weightKg;
    public float hipsCm;
    public float waistCm;
    public float glutesCm;
    public float thighLeftCm;
    public float thighRightCm;
    public float bustCm;
    public String notes;

    public BodyMeasurement() {}

    public BodyMeasurement(long dateRecorded, float weightKg, float hipsCm,
                           float waistCm, float glutesCm, float thighLeftCm,
                           float thighRightCm) {
        this.dateRecorded = dateRecorded;
        this.weightKg = weightKg;
        this.hipsCm = hipsCm;
        this.waistCm = waistCm;
        this.glutesCm = glutesCm;
        this.thighLeftCm = thighLeftCm;
        this.thighRightCm = thighRightCm;
    }
}
