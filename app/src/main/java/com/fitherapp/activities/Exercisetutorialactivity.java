package com.fitherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.fitherapp.R;
import com.fitherapp.models.Exercise;
import com.google.android.material.appbar.MaterialToolbar;

public class Exercisetutorialactivity extends AppCompatActivity {

    public static final String EXTRA_EXERCISE_NAME = "exercise_name";
    public static final String EXTRA_EXERCISE_CATEGORY = "exercise_category";
    public static final String EXTRA_EXERCISE_MUSCLES = "exercise_muscles";
    public static final String EXTRA_EXERCISE_SECONDARY = "exercise_secondary";
    public static final String EXTRA_EXERCISE_INSTRUCTIONS = "exercise_instructions";
    public static final String EXTRA_EXERCISE_TIPS = "exercise_tips";
    public static final String EXTRA_EXERCISE_MISTAKES = "exercise_mistakes";
    public static final String EXTRA_EXERCISE_BREATHING = "exercise_breathing";
    public static final String EXTRA_EXERCISE_DIFFICULTY = "exercise_difficulty";
    public static final String EXTRA_EXERCISE_EQUIPMENT = "exercise_equipment";
    public static final String EXTRA_EXERCISE_PROGRESSION = "exercise_progression";
    public static final String EXTRA_EXERCISE_DESCRIPTION = "exercise_description";
    public static final String EXTRA_EXERCISE_CALORIES = "exercise_calories";
    public static final String EXTRA_EXERCISE_TYPE = "exercise_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tutorial);

        String name = getIntent().getStringExtra(EXTRA_EXERCISE_NAME);
        String category = getIntent().getStringExtra(EXTRA_EXERCISE_CATEGORY);
        String muscles = getIntent().getStringExtra(EXTRA_EXERCISE_MUSCLES);
        String secondary = getIntent().getStringExtra(EXTRA_EXERCISE_SECONDARY);
        String instructions = getIntent().getStringExtra(EXTRA_EXERCISE_INSTRUCTIONS);
        String tips = getIntent().getStringExtra(EXTRA_EXERCISE_TIPS);
        String mistakes = getIntent().getStringExtra(EXTRA_EXERCISE_MISTAKES);
        String breathing = getIntent().getStringExtra(EXTRA_EXERCISE_BREATHING);
        String difficulty = getIntent().getStringExtra(EXTRA_EXERCISE_DIFFICULTY);
        String equipment = getIntent().getStringExtra(EXTRA_EXERCISE_EQUIPMENT);
        String progression = getIntent().getStringExtra(EXTRA_EXERCISE_PROGRESSION);
        String description = getIntent().getStringExtra(EXTRA_EXERCISE_DESCRIPTION);
        String type = getIntent().getStringExtra(EXTRA_EXERCISE_TYPE);
        int calories = getIntent().getIntExtra(EXTRA_EXERCISE_CALORIES, 5);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        toolbar.setNavigationOnClickListener(v -> finish());

        setField(R.id.tvExerciseName, name);
        setField(R.id.tvCategory, category);
        setField(R.id.tvDifficulty, difficulty);
        setField(R.id.tvType, type);
        setField(R.id.tvDescription, description);
        setField(R.id.tvPrimaryMuscles, muscles);
        setField(R.id.tvSecondaryMuscles, secondary != null && !secondary.isEmpty() ? secondary : "—");
        setField(R.id.tvEquipment, equipment != null && !equipment.isEmpty() ? equipment : "None");
        setField(R.id.tvCaloriesPerMin, calories + " kcal/min");
        setField(R.id.tvInstructions, instructions);
        setField(R.id.tvCoachingTips, tips != null && !tips.isEmpty() ? tips : "Focus on quality reps over speed.");
        setField(R.id.tvCommonMistakes, mistakes != null && !mistakes.isEmpty() ? mistakes : "Rushing through reps.");
        setField(R.id.tvBreathing, breathing != null && !breathing.isEmpty() ? breathing : "Breathe rhythmically throughout.");
        setField(R.id.tvProgression, progression != null && !progression.isEmpty() ? progression : "Increase reps or sets over time.");

        int color = getCategoryColor(category);
        View stripe = findViewById(R.id.viewCategoryStripe);
        if (stripe != null) stripe.setBackgroundColor(color);

        ImageView imgDemo = findViewById(R.id.imgExerciseDemo);
        if (imgDemo != null) imgDemo.setBackgroundColor(color);
    }

    private void setField(int id, String value) {
        TextView tv = findViewById(id);
        if (tv != null && value != null) tv.setText(value);
    }

    private int getCategoryColor(String category) {
        if (category == null) return 0xFF6A1B9A;
        switch (category.toUpperCase()) {
            case "GLUTES":   return 0xFF6A1B9A;
            case "LEGS":     return 0xFF1565C0;
            case "ABS":      return 0xFFB71C1C;
            case "ARMS":     return 0xFF2E7D32;
            case "BACK":     return 0xFF4E342E;
            case "WARMUP":   return 0xFFE65100;
            case "COOLDOWN": return 0xFF00695C;
            default:         return 0xFF37474F;
        }
    }

    public static void launch(android.app.Activity activity, Exercise ex) {
        Intent intent = new Intent(activity, Exercisetutorialactivity.class);
        intent.putExtra(EXTRA_EXERCISE_NAME, ex.name);
        intent.putExtra(EXTRA_EXERCISE_CATEGORY, ex.category);
        intent.putExtra(EXTRA_EXERCISE_MUSCLES, ex.targetMuscles);
        intent.putExtra(EXTRA_EXERCISE_SECONDARY, ex.secondaryMuscles);
        intent.putExtra(EXTRA_EXERCISE_INSTRUCTIONS, ex.instructions);
        intent.putExtra(EXTRA_EXERCISE_TIPS, ex.tips);
        intent.putExtra(EXTRA_EXERCISE_MISTAKES, ex.commonMistakes);
        intent.putExtra(EXTRA_EXERCISE_BREATHING, ex.breathingCue);
        intent.putExtra(EXTRA_EXERCISE_DIFFICULTY, ex.difficulty);
        intent.putExtra(EXTRA_EXERCISE_EQUIPMENT, ex.equipmentNeeded);
        intent.putExtra(EXTRA_EXERCISE_PROGRESSION, ex.progressionTip);
        intent.putExtra(EXTRA_EXERCISE_DESCRIPTION, ex.description);
        intent.putExtra(EXTRA_EXERCISE_CALORIES, ex.caloriesPerMin);
        intent.putExtra(EXTRA_EXERCISE_TYPE, ex.type);
        activity.startActivity(intent);
    }
}