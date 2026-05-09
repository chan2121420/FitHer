package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.BodyMeasurement;
import java.util.List;

@Dao
interface BodyMeasurementDao {
    @Insert
    long insert(BodyMeasurement m);
    @Query("SELECT * FROM body_measurements ORDER BY dateRecorded DESC")
    LiveData<List<BodyMeasurement>> getAll();
    @Query("SELECT * FROM body_measurements ORDER BY dateRecorded DESC LIMIT 1")
    LiveData<BodyMeasurement> getLatest();
}