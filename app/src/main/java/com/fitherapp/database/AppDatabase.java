package com.fitherapp.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.fitherapp.models.*;

@Database(
        entities = {
                User.class,
                Exercise.class,
                WorkoutPlan.class,
                WorkoutExercise.class,
                WorkoutHistory.class,
                WorkoutDay.class,
                WorkoutSession.class,
                BodyMeasurement.class,
                NutritionLog.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutPlanDao workoutPlanDao();
    public abstract WorkoutExerciseDao workoutExerciseDao();
    public abstract WorkoutDayDao workoutDayDao();
    public abstract WorkoutSessionDao workoutSessionDao();
    public abstract WorkoutHistoryDao workoutHistoryDao();
    public abstract BodyMeasurementDao bodyMeasurementDao();
    public abstract NutritionLogDao nutritionLogDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "fither_v2_database"
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}