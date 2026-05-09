package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fitherapp.models.WorkoutDay;

import java.util.List;

@Dao
public interface WorkoutDayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WorkoutDay day);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutDay> days);

    @Query("SELECT * FROM workout_days ORDER BY dayOfWeek ASC")
    LiveData<List<WorkoutDay>> getAllDays();

    @Query("SELECT COUNT(*) FROM workout_days")
    int count();
}
