package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.NutritionLog;
import java.util.List;

@Dao
public interface NutritionLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NutritionLog log);
    @Update
    void update(NutritionLog log);
    @Query("SELECT * FROM nutrition_logs ORDER BY dateRecorded DESC")
    LiveData<List<NutritionLog>> getAll();
}