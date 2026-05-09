package com.fitherapp.database;

import android.content.Context;
import com.fitherapp.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataSeeder {

    public static void seedIfEmpty(Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            if (db.exerciseDao().count() > 0) return;
            seedExercises(db);
            seedPlans(db);
        });
    }

    private static void seedExercises(AppDatabase db) {
        List<Exercise> exercises = new ArrayList<>();

        // ───── GLUTES - BODYWEIGHT ─────
        exercises.add(new Exercise("Glute Bridge", "GLUTES", "BODYWEIGHT",
                "The foundation of glute training. Perfect form builds the mind-muscle connection.",
                "1. Lie on back, knees bent 90°, feet flat hip-width.\n2. Press through heels and upper back.\n3. Drive hips up until body is straight from shoulders to knees.\n4. Squeeze glutes HARD at top — hold 2 seconds.\n5. Lower slowly in 3 seconds.",
                "Glutes, Hamstrings", "Core, Lower Back",
                false, 20, 0, 3, 30, "BEGINNER",
                "Think about pushing your knees out as you lift — this activates glute medius. Imagine you're cracking a walnut between your cheeks.",
                "Arching the lower back at the top. Letting knees cave inward. Rushing through the movement.",
                "Exhale as you drive hips up. Inhale as you lower.",
                "None", 5, false, "Add a resistance band above knees or progress to single-leg bridges."));

        exercises.add(new Exercise("Hip Thrust (Shoulder-Elevated)", "GLUTES", "BODYWEIGHT",
                "King of glute exercises. Greater range of motion than glute bridge.",
                "1. Sit with upper back against a bench/couch edge.\n2. Feet flat, hip-width, knees at 90° at top.\n3. Drive hips up explosively, squeeze hard at lockout.\n4. Lower slowly — 3 count down.\n5. Keep chin tucked, don't hyperextend spine.",
                "Glutes, Hamstrings", "Core, Hip Flexors",
                false, 15, 0, 3, 45, "BEGINNER",
                "At full lockout, hips should be level — not tilted. Drive through the HEEL, not toe.",
                "Letting hips drop on one side. Overextending at the top causing lower back strain.",
                "Big exhale at top contraction. Controlled inhale on the way down.",
                "Bench or couch", 6, false, "Add weight across hips. Progress to barbell hip thrust."));

        exercises.add(new Exercise("Single-Leg Glute Bridge", "GLUTES", "BODYWEIGHT",
                "Unilateral work fixes imbalances and doubles the intensity.",
                "1. Lie on back, one knee bent, other leg extended.\n2. Drive through the bent leg heel, lift extended leg off floor slightly.\n3. Raise hips high, squeeze glute on working side.\n4. Hold 1 second at top.\n5. Lower slowly. Complete all reps, then switch.",
                "Glutes, Hamstrings", "Core, Stabilizers",
                false, 12, 0, 3, 30, "INTERMEDIATE",
                "Keep hips level — fight the rotation. The non-working leg guides balance.",
                "Letting the hip on the non-working side drop. Rushing to hide imbalances.",
                "Exhale up. Inhale down.",
                "None", 6, true, "Elevate shoulders on a bench for full hip thrust range."));

        exercises.add(new Exercise("Donkey Kicks", "GLUTES", "BODYWEIGHT",
                "Isolation move for the glute max with perfect form cues.",
                "1. Start on hands and knees — wrists under shoulders, knees under hips.\n2. Keep core braced, back FLAT — don't rotate.\n3. Kick one leg back and up, foot flexed.\n4. Stop when thigh is parallel to floor — going higher uses lower back.\n5. Squeeze glute 1 second at top. Lower slowly.",
                "Glutes", "Lower Back, Hamstrings",
                false, 20, 0, 3, 30, "BEGINNER",
                "Think 'stomp the ceiling' with your heel. Slow and controlled beats fast every time.",
                "Rotating the torso to kick higher. Letting the back arch. Going too fast.",
                "Exhale on the kick. Inhale on the return.",
                "None", 5, true, "Add ankle weights or resistance band around thighs."));

        exercises.add(new Exercise("Fire Hydrant", "GLUTES", "BODYWEIGHT",
                "Targets glute medius for hip width and roundness.",
                "1. On all fours, core braced.\n2. Keeping knee bent at 90°, lift leg out to the side.\n3. Raise until thigh is parallel to floor.\n4. Pause and squeeze at top.\n5. Lower slowly without touching down between reps.",
                "Glute Medius, Hip Abductors", "Glute Max, Core",
                false, 20, 0, 3, 30, "BEGINNER",
                "Imagine your leg is a gate swinging open. Keep the pelvis completely still.",
                "Rotating the whole pelvis to lift higher. Letting weight shift to the opposite side.",
                "Exhale lifting. Inhale lowering.",
                "None", 5, true, "Add resistance band above knees."));

        exercises.add(new Exercise("Sumo Squat", "GLUTES", "BODYWEIGHT",
                "Wide-stance squat that torches inner thighs and glutes.",
                "1. Feet wider than shoulders, toes out 45°.\n2. Hands clasped at chest or extended forward.\n3. Sit back and down, knees tracking over toes.\n4. Lower until thighs are parallel or below.\n5. Drive through heels to stand, squeeze glutes at top.",
                "Glutes, Inner Thighs, Quads", "Hamstrings, Calves",
                false, 15, 0, 3, 45, "BEGINNER",
                "Push knees OUT as you lower — pretend you're spreading the floor apart with your feet.",
                "Knees caving inward. Leaning too far forward. Not squatting deep enough.",
                "Inhale on the way down. Big exhale as you drive up.",
                "None", 7, false, "Hold a weight at chest. Add pulses at the bottom."));

        exercises.add(new Exercise("Bulgarian Split Squat", "GLUTES", "BODYWEIGHT",
                "The most effective single-leg exercise. Brutal but worth it.",
                "1. Stand 2 feet in front of a bench.\n2. Place rear foot on bench, laces down.\n3. Lower until front thigh is parallel — back knee barely touches floor.\n4. Keep torso upright, front shin vertical.\n5. Drive through front heel to stand. Full reps one side, then switch.",
                "Glutes, Quads", "Hamstrings, Hip Flexors, Core",
                false, 10, 0, 3, 60, "INTERMEDIATE",
                "Lean torso slightly forward (15°) to shift emphasis to glutes. The closer your front foot to the bench, the more quad-dominant.",
                "Front knee caving in. Leaning too far forward or backward. Rushing through.",
                "Inhale at top. Big exhale as you drive up from the bottom.",
                "Bench or chair", 8, true, "Hold dumbbells at sides or add a barbell."));

        exercises.add(new Exercise("Curtsy Lunge", "GLUTES", "BODYWEIGHT",
                "Unique movement for glute medius — builds hip roundness.",
                "1. Stand feet hip-width.\n2. Step one foot behind and across the other — like a curtsy.\n3. Lower back knee toward floor.\n4. Keep front knee tracking over toes.\n5. Drive through front heel to return. Alternate sides.",
                "Glutes, Glute Medius, Quads", "Inner Thighs, Hamstrings",
                false, 12, 0, 3, 45, "INTERMEDIATE",
                "Think of stepping into a curtsy, not a lunge. The cross-body motion is what activates glute med.",
                "Letting the front knee cave inward. Not stepping far enough behind.",
                "Inhale down. Exhale drive up.",
                "None", 7, true, "Add dumbbells at sides or a resistance band above knees."));

        exercises.add(new Exercise("Squat Pulse", "GLUTES", "BODYWEIGHT",
                "Isometric burn at the bottom of the squat. Destroys your quads and glutes.",
                "1. Lower into squat position until thighs are parallel.\n2. Stay LOW — no coming back up fully.\n3. Pulse 2-3 inches up and down rhythmically.\n4. Keep weight in heels, chest up.\n5. Burn through the whole set.",
                "Quads, Glutes", "Hamstrings, Calves",
                false, 0, 45, 3, 30, "BEGINNER",
                "The burn is the point — embrace it. Breathe rhythmically to push through.",
                "Coming up too high — that defeats the purpose. Letting knees cave in.",
                "Short sharp exhales as you pulse.",
                "None", 8, false, "Hold a weight at chest or increase duration."));

        exercises.add(new Exercise("Glute Kickback (Standing)", "GLUTES", "BODYWEIGHT",
                "Standing isolation for glute max — great for mind-muscle connection.",
                "1. Stand holding wall or chair for balance.\n2. Shift weight to one leg.\n3. Kick the other leg back and up — straight leg or slightly bent.\n4. Squeeze glute HARD at the top.\n5. Lower slowly. Don't let foot touch floor between reps.",
                "Glutes", "Lower Back, Hamstrings",
                false, 20, 0, 3, 30, "BEGINNER",
                "Lead with the heel, not the toe. Think about pushing something away behind you.",
                "Arching the lower back. Swinging the leg — use controlled glute power only.",
                "Exhale on the kick back.",
                "None (wall for balance)", 5, true, "Add ankle weights or cable attachment."));

        exercises.add(new Exercise("Hip Abduction (Side-Lying)", "GLUTES", "BODYWEIGHT",
                "Glute medius isolation lying down — no wobbling, pure activation.",
                "1. Lie on side, body in straight line.\n2. Bottom knee can bend slightly for stability.\n3. Top leg straight, toes pointing slightly down.\n4. Raise top leg 18-24 inches — STOP before hip flexor takes over.\n5. Hold 1 second. Lower slowly.",
                "Glute Medius, Hip Abductors", "TFL, Core",
                false, 20, 0, 3, 30, "BEGINNER",
                "Turn toes down slightly to internally rotate — this maximally activates glute medius.",
                "Letting the pelvis rock backward. Raising the leg too high — that's TFL, not glutes.",
                "Exhale lifting. Inhale lowering.",
                "None", 4, true, "Add ankle weight or resistance band above knees."));

        // ───── GLUTES - BANDS ─────
        exercises.add(new Exercise("Banded Glute Bridge", "GLUTES", "BANDS",
                "Classic bridge amped up — the band forces glutes to work harder.",
                "1. Place resistance band just above knees.\n2. Lie on back, knees bent, feet hip-width.\n3. Press knees OUT against band as you bridge up.\n4. Hold 2 seconds at top, squeeze hard.\n5. Lower in 3 seconds.",
                "Glutes, Glute Medius", "Hamstrings, Core",
                true, 20, 0, 3, 30, "BEGINNER",
                "The outward knee press is the key — it doubles the glute med activation.",
                "Letting knees cave in — that removes the whole point of the band.",
                "Exhale up, inhale down.",
                "Resistance band", 6, false, "Increase band resistance or progress to hip thrust."));

        exercises.add(new Exercise("Banded Clamshell", "GLUTES", "BANDS",
                "Glute medius builder — creates the side butt shelf appearance.",
                "1. Lie on side, hips bent 45°, knees bent 90°, band above knees.\n2. Keep feet together stacked.\n3. Open top knee toward ceiling like a clam opening.\n4. Open as far as possible WITHOUT rotating pelvis.\n5. Pause, squeeze. Lower slowly.",
                "Glute Medius, Hip External Rotators", "TFL, Glute Max",
                true, 20, 0, 3, 30, "BEGINNER",
                "Put your hand on your hip — if you feel it rotate, you went too far. Quality over range.",
                "Rolling back the pelvis to open wider. Going too fast to avoid the burn.",
                "Exhale opening. Inhale closing.",
                "Resistance band", 4, true, "Use heavier band. Progress to standing hip abduction with cable."));

        exercises.add(new Exercise("Banded Side Steps", "GLUTES", "BANDS",
                "The burn you feel walking out — non-stop glute med and min activation.",
                "1. Band around ankles (harder) or just above knees.\n2. Bend knees slightly — athletic squat position.\n3. Step sideways with wide steps, keeping tension in band.\n4. Don't let feet come together — keep tension constant.\n5. 20 steps each direction.",
                "Glute Medius, Glute Minimus, Hip Abductors", "Quads, Hamstrings",
                true, 20, 0, 3, 30, "BEGINNER",
                "Stay LOW throughout — the moment you stand up, the glutes disengage.",
                "Taking too-small steps. Standing up straight to cheat. Letting ankles roll in.",
                "Steady breathing throughout.",
                "Resistance band", 6, false, "Walk forward and backward for added challenge."));

        exercises.add(new Exercise("Banded Donkey Kicks", "GLUTES", "BANDS",
                "Donkey kicks with resistance — every inch of the range is loaded.",
                "1. Loop band around one ankle, anchor to wrist or door.\n2. On all fours, core braced.\n3. Kick leg back against band resistance — controlled.\n4. Squeeze at top.\n5. Lower slowly — feel the band pull.",
                "Glutes", "Hamstrings, Lower Back",
                true, 15, 0, 3, 30, "INTERMEDIATE",
                "The band adds eccentric load on the way down — this is where growth happens. Don't let it snap back.",
                "Arching back. Going too fast. Using band with too much resistance that compromises form.",
                "Exhale kick, inhale return.",
                "Resistance band", 6, true, "Increase band resistance."));

        exercises.add(new Exercise("Banded Hip Thrust", "GLUTES", "BANDS",
                "Maximum glute activation — band adds top-range resistance where it matters most.",
                "1. Sit against bench, band above knees, feet hip-width.\n2. Press knees out against band.\n3. Drive hips up — hold 2 seconds at top.\n4. DOUBLE squeeze: glutes together AND knees out.\n5. Lower in 3 seconds.",
                "Glutes, Glute Medius", "Hamstrings, Core",
                true, 15, 0, 3, 45, "INTERMEDIATE",
                "The knee press-out at the top is extra work — don't skip it. This is what builds the upper glute.",
                "Not pressing knees out. Hyperextending the lower back at lockout.",
                "Big exhale at the top.",
                "Resistance band, bench", 7, false, "Use heavier band + add weight at hips."));

        exercises.add(new Exercise("Banded Fire Hydrant", "GLUTES", "BANDS",
                "Fire hydrant loaded — outer glute builder.",
                "1. Band above knees, on all fours.\n2. Open leg out to side against band resistance.\n3. Hold 1 second at top.\n4. Lower slowly — keep control against the band.\n5. Don't rush the return.",
                "Glute Medius, Hip Abductors", "Glute Max, Core",
                true, 15, 0, 3, 30, "INTERMEDIATE",
                "Slow the return phase — the eccentric with the band is where growth happens.",
                "Pivoting the pelvis. Letting band snap back.",
                "Exhale lifting, inhale lowering.",
                "Resistance band", 5, true, "Use heavier band or hold at top longer."));

        // ───── LEGS ─────
        exercises.add(new Exercise("Bodyweight Squat", "LEGS", "BODYWEIGHT",
                "The fundamental lower body movement. Master this before anything else.",
                "1. Feet shoulder-width, toes out 15-30°.\n2. Arms forward or at chest.\n3. Push knees out and sit back and down — hips break before knees.\n4. Reach depth where thighs are parallel or below.\n5. Drive through whole foot to stand. Squeeze glutes at top.",
                "Quads, Glutes", "Hamstrings, Calves, Core",
                false, 20, 0, 3, 45, "BEGINNER",
                "Think of sitting back onto a chair behind you. Keep chest tall throughout.",
                "Heels rising off floor. Knees caving inward. Rounding the lower back.",
                "Inhale as you descend. Exhale as you drive up.",
                "None", 7, false, "Add jump at top for jump squats. Add weight for goblet squats."));

        exercises.add(new Exercise("Reverse Lunge", "LEGS", "BODYWEIGHT",
                "Easier on knees than forward lunge — great for beginners.",
                "1. Stand tall, hands at hips or clasped.\n2. Step one foot BACK 2-3 feet.\n3. Lower back knee toward floor — 90° at both knees.\n4. Keep front shin vertical, torso upright.\n5. Drive through front heel to stand. Alternate or complete all reps one side.",
                "Quads, Glutes, Hamstrings", "Calves, Core",
                false, 12, 0, 3, 45, "BEGINNER",
                "Think straight DOWN with the back knee, not forward. Your front knee should be over your ankle.",
                "Front knee shooting past toes. Torso leaning forward. Taking too small a step.",
                "Inhale step back. Exhale drive up.",
                "None", 7, true, "Add weight. Progress to walking lunges or deficit lunges."));

        exercises.add(new Exercise("Jump Squat", "LEGS", "BODYWEIGHT",
                "Explosive power + serious calorie burn. Builds fast-twitch muscle fibers.",
                "1. Squat down to parallel.\n2. Swing arms back.\n3. EXPLODE upward — arms drive overhead.\n4. Land softly, bending knees immediately to absorb impact.\n5. Sink directly into next squat. No pausing.",
                "Quads, Glutes, Calves", "Hamstrings, Core",
                false, 15, 0, 3, 60, "INTERMEDIATE",
                "Land like a ninja — soft and quiet. Loud landing = bad for knees.",
                "Landing with straight knees (high injury risk). Not using arms for momentum.",
                "Exhale explosively on the jump.",
                "None", 12, false, "Add resistance band above knees."));

        exercises.add(new Exercise("Wall Sit", "LEGS", "BODYWEIGHT",
                "Isometric quad torture that builds mental toughness and raw strength.",
                "1. Back flat against smooth wall.\n2. Slide down until thighs are parallel to floor — 90° knees.\n3. Feet hip-width, flat on floor.\n4. Arms at sides or on thighs (not pushing down).\n5. Hold. Every second counts.",
                "Quads, Glutes", "Hamstrings, Calves",
                false, 0, 60, 3, 60, "BEGINNER",
                "Breathe rhythmically. Focus on a point ahead. Don't let the mind quit before the muscles.",
                "Thighs above parallel (too easy). Pushing on thighs with hands.",
                "Slow controlled breathing throughout.",
                "Wall", 6, false, "Hold longer. Add single-leg. Hold weights."));

        exercises.add(new Exercise("Step-Up", "LEGS", "BODYWEIGHT",
                "Functional strength — also a great glute activator.",
                "1. Stand facing a box, bench, or step (20-30cm high).\n2. Place full foot on step.\n3. Drive through the STEP FOOT heel — don't push off back foot.\n4. Bring both feet to top. Step back down with control.\n5. Complete all reps one leg, then switch.",
                "Quads, Glutes, Hamstrings", "Calves, Core",
                false, 12, 0, 3, 45, "BEGINNER",
                "The higher the step, the more glute activation. Leaning forward slightly targets glutes more.",
                "Pushing off the back leg — this is cheating. It's all about the lead leg.",
                "Exhale as you step up.",
                "Step, bench, or stairs", 7, true, "Add dumbbells. Increase step height."));

        exercises.add(new Exercise("Sumo Deadlift (Bodyweight)", "LEGS", "BODYWEIGHT",
                "Hip hinge pattern — teaches hamstring loading and hip extension.",
                "1. Wide stance, toes out 45°, hands clasped.\n2. Hinge at hips — push them BACK, not down.\n3. Lower torso until nearly parallel, back straight, slight knee bend.\n4. Feel hamstrings stretch.\n5. Drive hips forward to stand, squeeze glutes at top.",
                "Hamstrings, Glutes, Lower Back", "Quads, Core",
                false, 15, 0, 3, 45, "INTERMEDIATE",
                "Think of closing a car door with your hips on the way up. Hips drive forward, not up.",
                "Rounding the lower back. Squatting down instead of hinging back. Bar path not close to body.",
                "Inhale hinge back. Exhale drive hips forward.",
                "None", 7, false, "Add dumbbells or barbell for Romanian deadlift."));

        exercises.add(new Exercise("Lateral Lunge", "LEGS", "BODYWEIGHT",
                "Side-to-side movement hits inner thighs and glutes differently.",
                "1. Stand feet together.\n2. Step wide to the side — foot pointing forward.\n3. Sit into that hip, keeping other leg straight.\n4. Push through bending leg heel to return.\n5. Alternate sides.",
                "Quads, Glutes, Inner Thighs", "Hamstrings, Hip Abductors",
                false, 12, 0, 3, 45, "INTERMEDIATE",
                "Keep the moving foot pointing forward, not out — this changes the load on knee.",
                "Chest falling forward. Bending the straight leg. Taking too small a step.",
                "Inhale step out. Exhale drive back.",
                "None", 7, true, "Add weight. Progress to lateral lunge to curtsy lunge combo."));

        // ───── ABS & CORE ─────
        exercises.add(new Exercise("Plank", "ABS", "BODYWEIGHT",
                "The ultimate core stability exercise. Everything braced at once.",
                "1. Forearms on floor, elbows under shoulders.\n2. Body in a straight line from head to heels.\n3. Squeeze EVERYTHING: abs, glutes, quads, fists.\n4. Push floor away with forearms — creates lat engagement.\n5. Breathe steadily. Don't hold breath.",
                "Core, Transverse Abdominis", "Shoulders, Glutes, Quads",
                false, 0, 45, 3, 30, "BEGINNER",
                "Imagine someone is about to punch you in the stomach — brace like that.",
                "Hips sagging. Hips piking too high. Holding breath. Head dropping.",
                "Slow rhythmic breathing throughout.",
                "None", 4, false, "Extend hold time. Add shoulder taps or feet elevation."));

        exercises.add(new Exercise("Dead Bug", "ABS", "BODYWEIGHT",
                "Anti-rotation core exercise — protects lower back while building deep core.",
                "1. Lie on back, arms straight up, knees at 90° — all four limbs in the air.\n2. Press lower back INTO floor — this must not change.\n3. Slowly extend opposite arm and leg toward floor.\n4. Only go as low as you can without lower back lifting.\n5. Return and repeat opposite side.",
                "Core, Transverse Abdominis", "Hip Flexors, Shoulders",
                false, 10, 0, 3, 30, "BEGINNER",
                "The lower back staying flat is everything. If it lifts — reduce range of motion.",
                "Rushing through. Letting lower back arch. Moving both same-side limbs.",
                "Exhale extending out. Inhale returning.",
                "None", 4, false, "Add resistance band. Hold dumbbells. Straighten legs further."));

        exercises.add(new Exercise("Bicycle Crunch", "ABS", "BODYWEIGHT",
                "Most effective ab exercise according to research. Targets obliques.",
                "1. Lie on back, hands behind head — don't pull neck.\n2. Bring both knees to 90°.\n3. Rotate, bringing right elbow toward left knee while extending right leg.\n4. Switch sides in a continuous pedaling motion.\n5. Keep lower back pressed down.",
                "Obliques, Rectus Abdominis", "Hip Flexors, Core",
                false, 20, 0, 3, 30, "BEGINNER",
                "Slow and deliberate rotation beats fast cycling. You should feel the oblique crunch.",
                "Pulling on the neck. Rotating elbow to knee — instead rotate shoulder to knee.",
                "Exhale each rotation.",
                "None", 5, false, "Slow tempo. Pause at each rotation."));

        exercises.add(new Exercise("Russian Twist", "ABS", "BODYWEIGHT",
                "Oblique rotational work that sculpts the waist.",
                "1. Sit at 45° angle, knees bent, heels on floor or lifted.\n2. Clasp hands together in front.\n3. Rotate torso to one side, tap floor.\n4. Rotate back through center to opposite side.\n5. Keep chest up and back straight.",
                "Obliques, Rectus Abdominis", "Hip Flexors, Transverse Abdominis",
                false, 20, 0, 3, 30, "INTERMEDIATE",
                "The rotation comes from the torso — not the arms. Feel your oblique contract.",
                "Rounding the back. Only moving arms without rotating torso.",
                "Exhale each rotation.",
                "None", 5, false, "Lift feet off floor. Hold a weight."));

        exercises.add(new Exercise("Mountain Climbers", "ABS", "BODYWEIGHT",
                "Cardio meets core — full body conditioning in one movement.",
                "1. High plank position — hands under shoulders.\n2. Keep hips LOW and level.\n3. Drive one knee toward chest.\n4. Quickly switch legs in running motion.\n5. Keep core braced throughout — don't bob hips.",
                "Core, Hip Flexors, Shoulders", "Quads, Chest",
                false, 0, 30, 3, 30, "INTERMEDIATE",
                "Fast = cardio. Slow with 2 second pause = core strength. Both have value.",
                "Letting hips rise (plank collapse). Twisting hips side to side.",
                "Fast rhythmic breathing.",
                "None", 10, false, "Cross-body (knee to opposite elbow) for obliques."));

        exercises.add(new Exercise("Hollow Body Hold", "ABS", "BODYWEIGHT",
                "Gymnastics-based core move — teaches total-body tension.",
                "1. Lie on back, arms extended overhead.\n2. Press lower back into floor.\n3. Lift arms, head, shoulders and legs off floor.\n4. Body forms a banana shape — concave.\n5. Hold. Lower back must stay on floor.",
                "Core, Transverse Abdominis", "Hip Flexors, Shoulders",
                false, 0, 30, 3, 30, "INTERMEDIATE",
                "Legs lower = harder. Bend knees to make easier. The lower back contact is non-negotiable.",
                "Lower back arching off floor. Holding breath.",
                "Slow controlled breathing.",
                "None", 5, false, "Lower legs closer to floor. Add rocking motion."));

        exercises.add(new Exercise("Leg Raises", "ABS", "BODYWEIGHT",
                "Lower ab and hip flexor isolator — builds the lower abdominal area.",
                "1. Lie flat on back, hands under lower back for support.\n2. Legs together, raise to 90°.\n3. Lower SLOWLY — 3-4 seconds — stopping just above floor.\n4. Don't let lower back arch.\n5. Raise back up.",
                "Lower Abs, Hip Flexors", "Core, Inner Thighs",
                false, 15, 0, 3, 30, "INTERMEDIATE",
                "The lowering phase is where you build strength. Don't drop legs.",
                "Letting lower back arch off floor at the bottom. Bending knees too much.",
                "Exhale raising. Inhale lowering slowly.",
                "None", 5, false, "Hanging leg raises. Add ankle weights."));

        exercises.add(new Exercise("Toe Taps (Tabletop)", "ABS", "BODYWEIGHT",
                "Low-back safe core move — great for beginners learning to brace.",
                "1. Lie on back, legs in tabletop (90° knees).\n2. Arms at sides.\n3. Keep lower back flat — brace abs.\n4. Slowly lower one foot to tap floor.\n5. Bring back to tabletop. Alternate.",
                "Lower Abs, Core", "Hip Flexors",
                false, 20, 0, 3, 30, "BEGINNER",
                "Go SLOW. This only works if your lower back stays flat the entire time.",
                "Rushing. Letting lower back arch when foot approaches floor.",
                "Exhale lowering. Inhale returning.",
                "None", 4, false, "Straighten legs to make harder."));

        // ───── ARMS & UPPER BODY ─────
        exercises.add(new Exercise("Push-Up", "ARMS", "BODYWEIGHT",
                "The fundamental upper body push. Chest, shoulders, and triceps in one move.",
                "1. Hands slightly wider than shoulders.\n2. Body straight from head to heels — full plank.\n3. Lower chest to floor — elbows at 45° from body.\n4. Push back up to full arm extension.\n5. Don't let hips sag.",
                "Chest, Triceps, Shoulders", "Core, Serratus",
                false, 15, 0, 3, 45, "BEGINNER",
                "Think of pushing the floor away from you. Tuck elbows — don't flare them out.",
                "Hips sagging (weak core). Elbows flaring out to 90° (stresses shoulders). Partial range.",
                "Inhale down. Exhale pushing up.",
                "None", 8, false, "Wide grip (chest). Close grip (triceps). Decline (upper chest)."));

        exercises.add(new Exercise("Tricep Dips", "ARMS", "BODYWEIGHT",
                "Bench dips for pure tricep isolation — works all three heads.",
                "1. Hands on edge of bench/chair, fingers forward.\n2. Legs extended in front or bent.\n3. Lower body by bending elbows straight back.\n4. Lower until upper arm is parallel to floor.\n5. Push back up to straight arms.",
                "Triceps", "Shoulders, Chest",
                false, 15, 0, 3, 45, "BEGINNER",
                "Keep body close to bench. Elbows go straight back — not out to sides.",
                "Flaring elbows out. Hunching shoulders up. Not going deep enough.",
                "Inhale down. Exhale up.",
                "Bench or sturdy chair", 6, false, "Lift one leg. Add weight on lap. Parallel bars."));

        exercises.add(new Exercise("Diamond Push-Up", "ARMS", "BODYWEIGHT",
                "Close-grip push-up that maximally loads the triceps.",
                "1. Form diamond shape with thumbs and index fingers.\n2. Place hands under chest (not face).\n3. Lower chest toward hands — elbows skim body.\n4. Push back up.",
                "Triceps, Inner Chest", "Shoulders, Core",
                false, 10, 0, 3, 45, "INTERMEDIATE",
                "Keep hands under chest, not face. Elbows should brush your ribs.",
                "Hands too high (near face). Flaring elbows. Partial range of motion.",
                "Inhale down. Exhale up.",
                "None", 8, false, "Elevate feet for more tricep load."));

        exercises.add(new Exercise("Banded Bicep Curl", "ARMS", "BANDS",
                "Band curl for bicep isolation — constant tension = great pump.",
                "1. Stand on center of resistance band, feet hip-width.\n2. Hold ends with palms up.\n3. Curl hands toward shoulders — elbows stay fixed at sides.\n4. Squeeze bicep HARD at top.\n5. Lower slowly in 3 seconds.",
                "Biceps Brachii", "Forearms, Brachialis",
                true, 15, 0, 3, 30, "BEGINNER",
                "At the top, supinate (rotate) wrists outward — this peaks the bicep.",
                "Swinging body. Elbows drifting forward. Dropping weight too fast.",
                "Exhale curling up. Inhale lowering.",
                "Resistance band", 5, false, "Heavier band. Slow negative (4 seconds down)."));

        exercises.add(new Exercise("Banded Tricep Pushdown", "ARMS", "BANDS",
                "Overhead band anchored — targets long head of triceps.",
                "1. Anchor band overhead at door or pull-up bar.\n2. Face anchor, hold band at chest height.\n3. Push down extending arms fully.\n4. Squeeze triceps hard at full extension.\n5. Return slowly to start.",
                "Triceps", "Forearms",
                true, 15, 0, 3, 30, "BEGINNER",
                "Keep elbows fixed at sides — they should not move forward or back.",
                "Letting elbows flare. Not fully extending. Moving whole body.",
                "Exhale pushing down. Inhale returning.",
                "Resistance band, door anchor", 5, false, "Heavier band. Reverse grip for brachialis."));

        exercises.add(new Exercise("Pike Push-Up", "ARMS", "BODYWEIGHT",
                "Shoulder-dominant push-up — builds overhead pressing strength.",
                "1. Start in downward dog position — hips high, body inverted V.\n2. Bend elbows, lowering top of head toward floor.\n3. Keep elbows in line with shoulders.\n4. Push back up to inverted V.",
                "Shoulders, Triceps", "Core, Upper Chest",
                false, 10, 0, 3, 45, "INTERMEDIATE",
                "The more vertical your torso, the more shoulder-dominant it becomes. Walk feet in to increase angle.",
                "Flaring elbows wide. Not controlling the descent.",
                "Inhale down. Exhale up.",
                "None", 7, false, "Elevate feet on bench for handstand push-up progression."));

        // ───── BACK ─────
        exercises.add(new Exercise("Superman Hold", "BACK", "BODYWEIGHT",
                "Spinal erector and lower back strengthener — prevents injury.",
                "1. Lie face down, arms extended overhead.\n2. Simultaneously lift arms, chest, and legs off floor.\n3. Squeeze glutes and back muscles hard.\n4. Hold at top — body forms a slight arc.\n5. Lower slowly.",
                "Erector Spinae, Glutes", "Hamstrings, Rear Delts",
                false, 0, 30, 3, 30, "BEGINNER",
                "Think long — reach arms forward and heels backward simultaneously.",
                "Straining the neck by looking forward. Not squeezing glutes.",
                "Exhale as you lift. Breathe steadily holding.",
                "None", 4, false, "Hold longer. Add Y-raises (arms in Y shape)."));

        exercises.add(new Exercise("Bird Dog", "BACK", "BODYWEIGHT",
                "The ultimate anti-rotation stability exercise — rehab staple.",
                "1. On hands and knees — wrists under shoulders, knees under hips.\n2. Brace core.\n3. Extend RIGHT arm and LEFT leg simultaneously — straight.\n4. Hold 3 seconds — keep hips level. No rotation.\n5. Return slowly. Alternate.",
                "Core, Erector Spinae", "Glutes, Shoulders, Hamstrings",
                false, 10, 0, 3, 30, "BEGINNER",
                "Place a water bottle on your lower back — it shouldn't spill. This tests your level.",
                "Rotating hips. Lifting leg too high (causes lower back arch). Rushing.",
                "Exhale extending. Inhale returning.",
                "None", 4, false, "Add resistance band around wrists/ankles."));

        exercises.add(new Exercise("Banded Pull-Apart", "BACK", "BANDS",
                "Rear delt and upper back builder — fixes posture and shoulder health.",
                "1. Hold band with both hands, arms straight in front at shoulder height.\n2. Pull band apart until arms are wide open — chest level.\n3. Squeeze shoulder blades together hard.\n4. Hold 1 second.\n5. Return slowly — don't let band snap.",
                "Rear Delts, Upper Back, Rhomboids", "Rotator Cuff",
                true, 20, 0, 3, 30, "BEGINNER",
                "Think about putting your shoulder blades in your back pockets as you pull apart.",
                "Letting shoulders shrug up. Only pulling with hands — engage the back muscles.",
                "Exhale pulling apart. Inhale returning.",
                "Resistance band", 4, false, "Use heavier band. Slow the negative."));

        exercises.add(new Exercise("Inverted Row (Under Table)", "BACK", "BODYWEIGHT",
                "DIY horizontal pull — counterpart to the push-up for balanced upper body.",
                "1. Lie under a sturdy table.\n2. Grip edge of table, hands shoulder-width.\n3. Hang with straight body (heels on floor).\n4. Pull chest to table edge — squeeze shoulder blades.\n5. Lower fully.",
                "Upper Back, Lats, Biceps", "Core, Rear Delts",
                false, 12, 0, 3, 45, "INTERMEDIATE",
                "Think about pulling your elbows to your back pockets — this engages back vs. arms.",
                "Not straightening body (too easy). Letting shoulders shrug up.",
                "Exhale pulling up. Inhale lowering.",
                "Sturdy table", 7, false, "Elevate feet. Move hands closer to body."));

        // ───── FULL BODY / CARDIO ─────
        exercises.add(new Exercise("Burpee", "FULL_BODY", "BODYWEIGHT",
                "The total conditioning exercise — no equipment, maximum output.",
                "1. Stand → squat and place hands on floor.\n2. Jump feet back to plank.\n3. Perform one push-up (optional).\n4. Jump feet forward to squat.\n5. Jump up explosively — arms overhead.",
                "Full Body", "Cardio, Core",
                false, 10, 0, 3, 60, "INTERMEDIATE",
                "Break it into pieces if needed. Step back instead of jump for lower impact.",
                "Landing with straight legs. Not going through full range of motion.",
                "Exhale on the jump up.",
                "None", 15, false, "Add push-up. Box jump at the top."));

        exercises.add(new Exercise("Jump Rope (No Rope)", "FULL_BODY", "CARDIO",
                "Mimic jump rope without equipment — great cardio warmup.",
                "1. Stand with slight knee bend.\n2. Jump on balls of feet — small hops.\n3. Rotate wrists as if turning rope.\n4. Stay light on feet — minimal impact.\n5. Keep rhythm.",
                "Calves, Cardio, Coordination", "Full Body",
                false, 0, 60, 1, 0, "BEGINNER",
                "Stay on the balls of your feet throughout. Think light and fast.",
                "Landing flat-footed. Jumping too high — waste of energy.",
                "Steady rhythmic breathing.",
                "None", 10, false, "Increase speed. Add double-unders simulation."));

        exercises.add(new Exercise("High Knees", "FULL_BODY", "CARDIO",
                "Running in place with emphasis — elevates heart rate fast.",
                "1. Run in place.\n2. Drive knees as HIGH as possible — hip height target.\n3. Pump arms rhythmically.\n4. Stay on balls of feet.\n5. Keep core braced.",
                "Hip Flexors, Quads, Cardio", "Core, Calves",
                false, 0, 30, 3, 30, "BEGINNER",
                "Quality knees high > fast but lazy. Slow if needed to get knees up.",
                "Not driving knees high enough. Leaning back.",
                "Rhythmic breathing.",
                "None", 12, false, "Add resistance band above knees."));

        exercises.add(new Exercise("Bear Crawl", "FULL_BODY", "BODYWEIGHT",
                "Primal movement pattern — total body stability and strength.",
                "1. Hands and feet on floor, knees 1 inch off ground.\n2. Brace core — back flat.\n3. Move opposite arm and foot forward simultaneously.\n4. Keep hips low and level.\n5. Travel forward 10 steps, then backward 10.",
                "Core, Shoulders, Quads", "Glutes, Triceps",
                false, 0, 30, 3, 30, "INTERMEDIATE",
                "Slow is hard. The slower you go, the more stability is required.",
                "Hips piking up. Crawling too fast. Moving same-side limbs.",
                "Steady breathing throughout.",
                "None", 8, false, "Lateral bear crawl. Add weight vest."));

        exercises.add(new Exercise("Skater Jumps", "FULL_BODY", "BODYWEIGHT",
                "Lateral power and glute activation — mimics speed skating.",
                "1. Stand on one leg.\n2. Jump laterally to opposite leg.\n3. Land on single leg softly — absorb with bent knee.\n4. Swing arms for momentum.\n5. Continue side to side.",
                "Glutes, Quads, Calves", "Core, Hip Abductors",
                false, 0, 30, 3, 30, "INTERMEDIATE",
                "The landing is the exercise — absorb deeply before jumping again.",
                "Landing with a straight leg. Not landing on one foot.",
                "Exhale each landing.",
                "None", 10, false, "Touch floor with hand on each landing."));

        exercises.add(new Exercise("Box Jump", "FULL_BODY", "BODYWEIGHT",
                "Explosive lower body power — fast-twitch muscle fiber recruiter.",
                "1. Stand in front of box (30-60cm).\n2. Dip into quarter squat, swing arms.\n3. Jump EXPLOSIVELY, driving arms up.\n4. Land softly on box with bent knees.\n5. Step down (don't jump down).",
                "Quads, Glutes, Calves", "Hamstrings, Core",
                false, 10, 0, 3, 60, "INTERMEDIATE",
                "Step down — never jump down. The landing stress is doubled jumping off.",
                "Landing with locked knees. Jumping to a box that's too high.",
                "Exhale on takeoff.",
                "Sturdy box or step", 12, false, "Increase box height. Depth jump variations."));

        // ───── WARMUP ─────
        exercises.add(new Exercise("Leg Swings (Forward)", "WARMUP", "BODYWEIGHT",
                "Dynamic hip flexor and hamstring warmup — essential before lower body work.",
                "1. Stand sideways to wall, hand for balance.\n2. Swing one leg forward and back like a pendulum.\n3. Gradually increase range of motion.\n4. Keep core engaged.\n5. 15 swings each leg.",
                "Hip Flexors, Hamstrings", "Glutes, Hip Capsule",
                false, 15, 0, 1, 0, "BEGINNER",
                "Let momentum build gradually — don't force the range.",
                "Forcing range too quickly. Not stabilizing the standing leg.",
                "Breathe freely.",
                "Wall for balance", 4, true, "Lateral leg swings for hip abductors."));

        exercises.add(new Exercise("Hip Circles", "WARMUP", "BODYWEIGHT",
                "Lubricates the hip joint — critical before any glute or leg work.",
                "1. Hands on hips, feet hip-width.\n2. Draw large circles with your hips.\n3. 10 circles clockwise.\n4. 10 circles counter-clockwise.\n5. Gradually increase size.",
                "Hip Joint, Hip Flexors", "Lower Back, Glutes",
                false, 10, 0, 1, 0, "BEGINNER",
                "Imagine drawing the biggest circle possible with your hips.",
                "Moving from the knees — it should come from the hips.",
                "Free breathing.",
                "None", 3, false, "Single-leg version for more challenge."));

        exercises.add(new Exercise("Glute Activation Walk", "WARMUP", "BANDS",
                "Band walk warmup — fires up glutes before training.",
                "1. Band above knees, slight squat position.\n2. Walk forward 10 steps maintaining tension.\n3. Walk backward 10 steps.\n4. Walk sideways 10 steps each direction.\n5. Stay low the entire time.",
                "Glute Medius, Hip Abductors", "Quads, Glutes",
                true, 0, 60, 1, 0, "BEGINNER",
                "This IS the workout for some — don't skip it. The pump you get here = better mind-muscle later.",
                "Standing up straight. Letting band go slack.",
                "Steady breathing.",
                "Resistance band", 4, false, "Use heavier band."));

        exercises.add(new Exercise("Cat-Cow Stretch", "WARMUP", "BODYWEIGHT",
                "Spinal mobility that preps your entire core chain.",
                "1. On hands and knees.\n2. Inhale — drop belly toward floor, lift head and tailbone (cow).\n3. Exhale — round spine to ceiling, tuck pelvis and head (cat).\n4. Coordinate with breath.\n5. Flow for 10 reps.",
                "Spine, Core", "Hips, Shoulders",
                false, 10, 0, 1, 0, "BEGINNER",
                "Match movement to breath exactly — this is a breath-driven exercise.",
                "Rushing. Not going through full spinal flexion and extension.",
                "Inhale cow. Exhale cat.",
                "None", 2, false, "Add thread-the-needle rotation."));

        // ───── COOLDOWN ─────
        exercises.add(new Exercise("Pigeon Pose (Modified)", "COOLDOWN", "BODYWEIGHT",
                "Deep glute and hip opener — essential after any lower body session.",
                "1. From plank, bring right knee toward right wrist.\n2. Extend left leg behind you.\n3. Square hips toward floor as much as possible.\n4. Either stay upright or fold forward over front leg.\n5. Hold 30-60 seconds each side.",
                "Glutes, Piriformis, Hip Flexors", "IT Band, Lower Back",
                false, 0, 60, 1, 0, "BEGINNER",
                "Place a folded blanket under the hip if it doesn't reach the floor — this is fine.",
                "Rushing. Letting hip hike up instead of squaring toward floor.",
                "Long exhales to deepen the stretch.",
                "None (optional: blanket/block)", 2, true, "Deeper fold. Reclined pigeon."));

        exercises.add(new Exercise("Glute-Hip Flexor Stretch", "COOLDOWN", "BODYWEIGHT",
                "Lying figure-4 stretch that targets both glutes and hip flexors.",
                "1. Lie on back.\n2. Cross right ankle over left thigh (figure-4 position).\n3. Flex right foot to protect knee.\n4. Pull left thigh toward chest — feel stretch in right glute.\n5. Hold 30 seconds. Switch.",
                "Glutes, Piriformis", "Hip Flexors",
                false, 0, 60, 1, 0, "BEGINNER",
                "The closer you pull the leg to your chest, the deeper the stretch.",
                "Not flexing the foot (knee stress). Holding breath.",
                "Long exhales to release into the stretch.",
                "None", 2, true, "Standing figure-4 stretch at wall."));

        exercises.add(new Exercise("Child's Pose", "COOLDOWN", "BODYWEIGHT",
                "Full body reset — calms nervous system and stretches everything.",
                "1. Kneel, sink hips back toward heels.\n2. Extend arms forward on floor.\n3. Rest forehead down.\n4. Breathe deeply into back of ribcage.\n5. Hold and fully relax.",
                "Lower Back, Hips, Shoulders", "Ankles, Knees",
                false, 0, 60, 1, 0, "BEGINNER",
                "Arms wide = shoulder stretch. Arms at sides = deeper back release.",
                "None — this is the easiest thing in the world. Just breathe.",
                "Deep belly breathing throughout.",
                "None", 2, false, "Wide-knee child's pose for inner thigh release."));

        exercises.add(new Exercise("Standing Quad Stretch", "COOLDOWN", "BODYWEIGHT",
                "Essential after all leg and glute work — quad and hip flexor release.",
                "1. Stand on one leg (hand on wall for balance).\n2. Bend knee, grab ankle behind you.\n3. Pull heel toward glute.\n4. Keep knees TOGETHER — don't let bending knee drift out.\n5. Hold 30 seconds. Switch.",
                "Quadriceps, Hip Flexors", "Knee Joint",
                false, 0, 60, 1, 0, "BEGINNER",
                "Tuck pelvis under (posterior tilt) to increase the stretch into hip flexor.",
                "Letting the knee drift out. Leaning forward to grab the foot.",
                "Deep slow breaths.",
                "Wall for balance", 2, true, "No wall (balance challenge)."));

        exercises.add(new Exercise("Seated Hamstring Stretch", "COOLDOWN", "BODYWEIGHT",
                "Post-workout hamstring release to reduce soreness.",
                "1. Sit on floor, one leg extended, other bent inward.\n2. Sit tall — don't round spine.\n3. Hinge forward from HIPS — not back.\n4. Reach toward foot.\n5. Hold 30 seconds each side.",
                "Hamstrings", "Lower Back, Calves",
                false, 0, 60, 1, 0, "BEGINNER",
                "Use a strap or towel around foot if you can't reach. The key is hinging from hips, not rounding back.",
                "Rounding the back (stretches back, not hamstrings).",
                "Inhale to sit tall. Exhale to fold deeper.",
                "None", 2, true, "Use towel or strap for tighter hamstrings."));

        db.exerciseDao().insertAll(exercises);
    }

    private static void seedPlans(AppDatabase db) {
        List<WorkoutPlan> plans = new ArrayList<>();

        // Plan 1
        WorkoutPlan p1 = new WorkoutPlan("Glute Activation — Zero to Lift",
                "Master mind-muscle connection. If you're new or returning, start here. 4 targeted exercises + cooldown.", "BEGINNER", "GLUTES", 22, 130);
        p1.daysPerWeek = 3; p1.equipment = "NONE"; p1.exerciseCount = 5;
        plans.add(p1);

        // Plan 2
        WorkoutPlan p2 = new WorkoutPlan("Banded Booty Blast",
                "Resistance band circuit that burns the glutes from every angle. Maximum activation.", "INTERMEDIATE", "GLUTES", 28, 175);
        p2.daysPerWeek = 4; p2.equipment = "BANDS"; p2.exerciseCount = 6;
        plans.add(p2);

        // Plan 3
        WorkoutPlan p3 = new WorkoutPlan("Glute & Hip Width Builder",
                "Grow both glute max and medius for the full round shape. Volume-focused.", "INTERMEDIATE", "GLUTES", 35, 210);
        p3.daysPerWeek = 4; p3.equipment = "BANDS"; p3.exerciseCount = 7;
        plans.add(p3);

        // Plan 4
        WorkoutPlan p4 = new WorkoutPlan("Lower Body Power",
                "Explosive movements for athletic legs. Burns serious calories.", "INTERMEDIATE", "LEGS", 30, 240);
        p4.daysPerWeek = 3; p4.equipment = "NONE"; p4.exerciseCount = 6;
        plans.add(p4);

        // Plan 5
        WorkoutPlan p5 = new WorkoutPlan("Leg Day: Sculpt & Tone",
                "Complete lower body shaping. Quads, hamstrings, calves — all covered.", "BEGINNER", "LEGS", 28, 190);
        p5.daysPerWeek = 3; p5.equipment = "NONE"; p5.exerciseCount = 6;
        plans.add(p5);

        // Plan 6
        WorkoutPlan p6 = new WorkoutPlan("Core Foundations",
                "Build a strong, stable core. Protects your back and gives you that flat stomach.", "BEGINNER", "ABS", 20, 120);
        p6.daysPerWeek = 4; p6.equipment = "NONE"; p6.exerciseCount = 6;
        plans.add(p6);

        // Plan 7
        WorkoutPlan p7 = new WorkoutPlan("Abs & Oblique Burn",
                "Waist-shaping core circuit. Targets all layers for definition.", "INTERMEDIATE", "ABS", 22, 145);
        p7.daysPerWeek = 4; p7.equipment = "NONE"; p7.exerciseCount = 6;
        plans.add(p7);

        // Plan 8
        WorkoutPlan p8 = new WorkoutPlan("Full Body Sculpt",
                "Complete body workout — hit every muscle group in one session. Best for busy days.", "INTERMEDIATE", "FULL_BODY", 40, 280);
        p8.daysPerWeek = 3; p8.equipment = "BANDS"; p8.exerciseCount = 8;
        plans.add(p8);

        // Plan 9
        WorkoutPlan p9 = new WorkoutPlan("HIIT Fat Burner",
                "High-intensity intervals for maximum calorie burn. Cardio meets strength.", "INTERMEDIATE", "FULL_BODY", 25, 320);
        p9.daysPerWeek = 3; p9.equipment = "NONE"; p9.exerciseCount = 6;
        plans.add(p9);

        // Plan 10
        WorkoutPlan p10 = new WorkoutPlan("Upper Body Tone",
                "Shoulders, arms, back. Build that sculpted upper body to match your lower body.", "BEGINNER", "ARMS", 25, 155);
        p10.daysPerWeek = 3; p10.equipment = "BANDS"; p10.exerciseCount = 6;
        plans.add(p10);

        // Plan 11
        WorkoutPlan p11 = new WorkoutPlan("Advanced Glute Protocol",
                "High volume, progressive overload. For those who want serious results.", "ADVANCED", "GLUTES", 45, 290);
        p11.daysPerWeek = 5; p11.equipment = "BANDS"; p11.exerciseCount = 9;
        plans.add(p11);

        // Plan 12
        WorkoutPlan p12 = new WorkoutPlan("Recovery & Mobility",
                "Active recovery day. Stretching, mobility, and movement quality.", "BEGINNER", "FULL_BODY", 20, 70);
        p12.daysPerWeek = 2; p12.equipment = "NONE"; p12.exerciseCount = 6;
        plans.add(p12);

        db.workoutPlanDao().insertAll(plans);

        // Seed exercises for each plan
        seedPlanExercises(db);
    }

    private static void seedPlanExercises(AppDatabase db) {
        // Plan 1 — Glute Activation Beginner
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(1, 37, 0, 1, 0, 30, 0, false, "Warmup"));
            wes.add(new WorkoutExercise(1, 1, 1, 3, 20, 0, 30, false, "Hold 2s at top. Slow down."));
            wes.add(new WorkoutExercise(1, 4, 2, 3, 15, 0, 30, true, "Keep back flat. Squeeze at top."));
            wes.add(new WorkoutExercise(1, 5, 3, 3, 20, 0, 30, true, "Open like a gate. Hold 1s."));
            wes.add(new WorkoutExercise(1, 6, 4, 3, 20, 0, 45, false, "Wide stance. Knees out."));
            wes.add(new WorkoutExercise(1, 46, 5, 1, 0, 60, 0, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 2 — Banded Booty Blast
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(2, 39, 0, 1, 0, 60, 0, false, "Warmup — keep band on"));
            wes.add(new WorkoutExercise(2, 12, 1, 4, 20, 0, 30, false, "Press knees OUT. Double squeeze."));
            wes.add(new WorkoutExercise(2, 13, 2, 3, 20, 0, 30, true, "Quality over speed."));
            wes.add(new WorkoutExercise(2, 14, 3, 3, 20, 0, 30, true, "Step WIDE. Stay low."));
            wes.add(new WorkoutExercise(2, 16, 4, 3, 15, 0, 30, true, "Slow return against band."));
            wes.add(new WorkoutExercise(2, 15, 5, 3, 15, 0, 45, false, "Full lockout. Hold 2s."));
            wes.add(new WorkoutExercise(2, 47, 6, 1, 0, 60, 0, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 3 — Glute & Hip Width Builder
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(3, 37, 0, 1, 0, 30, 0, false, "Warmup"));
            wes.add(new WorkoutExercise(3, 2, 1, 4, 15, 0, 45, false, "Full range hip thrust. 3s down."));
            wes.add(new WorkoutExercise(3, 11, 2, 3, 20, 0, 30, true, "Internal rotation tip — toes down."));
            wes.add(new WorkoutExercise(3, 7, 3, 3, 12, 0, 60, true, "SLOW descent. Stay upright."));
            wes.add(new WorkoutExercise(3, 8, 4, 3, 20, 0, 30, true, "Lead with heel. No rotation."));
            wes.add(new WorkoutExercise(3, 13, 5, 3, 20, 0, 30, true, "Control the opening AND closing."));
            wes.add(new WorkoutExercise(3, 10, 6, 3, 12, 0, 45, true, "Lean forward 15° — more glute."));
            wes.add(new WorkoutExercise(3, 47, 7, 1, 0, 60, 0, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 4 — Lower Body Power
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(4, 37, 0, 1, 0, 30, 0, false, "Warmup"));
            wes.add(new WorkoutExercise(4, 22, 1, 4, 15, 0, 60, false, "EXPLODE up. Land soft."));
            wes.add(new WorkoutExercise(4, 18, 2, 3, 12, 0, 60, true, "Drive through front heel ONLY."));
            wes.add(new WorkoutExercise(4, 26, 3, 3, 15, 0, 60, false, "Land ninja-quiet."));
            wes.add(new WorkoutExercise(4, 35, 4, 3, 0, 30, 30, false, "Stay low. Absorb each landing."));
            wes.add(new WorkoutExercise(4, 21, 5, 3, 15, 0, 60, false, "Wide stance. Drive knees out."));
            wes.add(new WorkoutExercise(4, 50, 6, 1, 0, 60, 0, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 5 — Leg Day Sculpt
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(5, 38, 0, 1, 0, 30, 0, false, "Warmup — open hips"));
            wes.add(new WorkoutExercise(5, 17, 1, 4, 20, 0, 45, false, "Sit back and down. Deep squat."));
            wes.add(new WorkoutExercise(5, 18, 2, 3, 12, 0, 45, true, "Step BACK, knee straight down."));
            wes.add(new WorkoutExercise(5, 23, 3, 3, 0, 60, 60, false, "90° knees. Arms off thighs."));
            wes.add(new WorkoutExercise(5, 25, 4, 3, 12, 0, 45, true, "Big step. Drive back through lead heel."));
            wes.add(new WorkoutExercise(5, 24, 5, 3, 12, 0, 45, true, "Lean forward slightly for more glute."));
            wes.add(new WorkoutExercise(5, 49, 6, 1, 0, 60, 0, false, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 6 — Core Foundations
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(6, 41, 0, 1, 10, 0, 0, false, "Warmup — spinal mobility"));
            wes.add(new WorkoutExercise(6, 27, 1, 3, 0, 40, 30, false, "Full body brace. Don't hold breath."));
            wes.add(new WorkoutExercise(6, 28, 2, 3, 10, 0, 30, false, "Lower back must stay flat."));
            wes.add(new WorkoutExercise(6, 33, 3, 3, 20, 0, 30, false, "Rotate torso, not just arms."));
            wes.add(new WorkoutExercise(6, 34, 4, 3, 20, 0, 30, false, "Slow each tap down."));
            wes.add(new WorkoutExercise(6, 42, 5, 3, 0, 30, 30, false, "Stay LOW. Core tight."));
            wes.add(new WorkoutExercise(6, 48, 6, 1, 0, 60, 0, false, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 7 — Abs & Oblique Burn
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(7, 27, 0, 3, 0, 45, 20, false, "Stay tight — full brace."));
            wes.add(new WorkoutExercise(7, 29, 1, 3, 20, 0, 30, false, "Slow rotation. Shoulder to knee."));
            wes.add(new WorkoutExercise(7, 30, 2, 3, 20, 0, 30, false, "Torso rotation — feel the oblique."));
            wes.add(new WorkoutExercise(7, 31, 3, 3, 15, 0, 30, false, "Slow lower. Don't arch back."));
            wes.add(new WorkoutExercise(7, 32, 4, 3, 0, 30, 30, false, "Hips LOW. Move fast."));
            wes.add(new WorkoutExercise(7, 36, 5, 3, 0, 30, 30, false, "Lower back DOWN always."));
            wes.add(new WorkoutExercise(7, 48, 6, 1, 0, 60, 0, false, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 8 — Full Body Sculpt
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(8, 37, 0, 1, 0, 30, 0, false, "Warmup"));
            wes.add(new WorkoutExercise(8, 1, 1, 3, 20, 0, 30, false, "Glutes on fire."));
            wes.add(new WorkoutExercise(8, 17, 2, 3, 15, 0, 45, false, "Squat deep."));
            wes.add(new WorkoutExercise(8, 27, 3, 3, 0, 40, 30, false, "Core tight."));
            wes.add(new WorkoutExercise(8, 28, 4, 3, 15, 0, 45, false, "Chest to floor."));
            wes.add(new WorkoutExercise(8, 43, 5, 3, 20, 0, 30, false, "Chest up. Pull blades together."));
            wes.add(new WorkoutExercise(8, 29, 6, 3, 20, 0, 30, false, "Slow rotation."));
            wes.add(new WorkoutExercise(8, 14, 7, 3, 20, 0, 30, false, "Band on — keep tension."));
            wes.add(new WorkoutExercise(8, 47, 8, 1, 0, 60, 0, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 9 — HIIT Fat Burner
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(9, 40, 0, 1, 0, 60, 0, false, "Warmup — get heart rate up"));
            wes.add(new WorkoutExercise(9, 33, 1, 4, 0, 30, 15, false, "Max effort. Go fast."));
            wes.add(new WorkoutExercise(9, 22, 2, 4, 0, 30, 15, false, "EXPLODE. Land soft."));
            wes.add(new WorkoutExercise(9, 34, 3, 4, 0, 30, 15, false, "Knees HIGH. Arms pump."));
            wes.add(new WorkoutExercise(9, 44, 4, 4, 0, 30, 15, false, "Stay light on feet."));
            wes.add(new WorkoutExercise(9, 35, 5, 4, 0, 30, 15, false, "Big jumps side to side."));
            wes.add(new WorkoutExercise(9, 45, 6, 3, 10, 0, 30, false, "Full burpee. Push-up included."));
            wes.add(new WorkoutExercise(9, 48, 7, 1, 0, 60, 0, false, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 10 — Upper Body Tone
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(10, 28, 0, 3, 12, 0, 45, false, "Controlled. Chest to floor."));
            wes.add(new WorkoutExercise(10, 37, 1, 3, 10, 0, 45, false, "Pike high. Elbows in."));
            wes.add(new WorkoutExercise(10, 29, 2, 3, 15, 0, 45, false, "Keep back close to bench."));
            wes.add(new WorkoutExercise(10, 43, 3, 3, 20, 0, 30, false, "Blades together. Hold 1s."));
            wes.add(new WorkoutExercise(10, 44, 4, 3, 15, 0, 30, false, "Elbows fixed. Squeeze at top."));
            wes.add(new WorkoutExercise(10, 30, 5, 3, 15, 0, 30, false, "Elbows to pockets. Push fully down."));
            wes.add(new WorkoutExercise(10, 48, 6, 1, 0, 60, 0, false, "Cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 11 — Advanced Glute Protocol
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(11, 39, 0, 1, 0, 60, 0, false, "Full warmup — band walk"));
            wes.add(new WorkoutExercise(11, 15, 1, 5, 15, 0, 45, false, "4-0-3 tempo. Full lockout."));
            wes.add(new WorkoutExercise(11, 2, 2, 4, 20, 0, 30, false, "Double squeeze at top."));
            wes.add(new WorkoutExercise(11, 7, 3, 4, 12, 0, 60, true, "Lean forward. Deep ROM."));
            wes.add(new WorkoutExercise(11, 8, 4, 4, 20, 0, 30, true, "No rotation. Maximum squeeze."));
            wes.add(new WorkoutExercise(11, 13, 5, 4, 20, 0, 30, true, "Heavy band. Full open."));
            wes.add(new WorkoutExercise(11, 9, 6, 3, 12, 0, 60, false, "Slow descent. Drive through heel."));
            wes.add(new WorkoutExercise(11, 3, 7, 4, 12, 0, 45, true, "Level hips. Full extension."));
            wes.add(new WorkoutExercise(11, 11, 8, 3, 20, 0, 30, true, "Toes down for max med activation."));
            wes.add(new WorkoutExercise(11, 47, 9, 1, 0, 60, 0, true, "Full cooldown"));
            db.workoutExerciseDao().insertAll(wes);
        }

        // Plan 12 — Recovery & Mobility
        {
            List<WorkoutExercise> wes = new ArrayList<>();
            wes.add(new WorkoutExercise(12, 41, 0, 1, 10, 0, 0, false, "Spinal warmup"));
            wes.add(new WorkoutExercise(12, 42, 1, 1, 0, 60, 0, false, "Bear crawl — slow and mindful"));
            wes.add(new WorkoutExercise(12, 43, 2, 1, 20, 0, 0, false, "Rear delt and posture reset"));
            wes.add(new WorkoutExercise(12, 46, 3, 1, 0, 60, 0, true, "Deep glute release"));
            wes.add(new WorkoutExercise(12, 49, 4, 1, 0, 60, 0, true, "Hamstring length"));
            wes.add(new WorkoutExercise(12, 50, 5, 1, 0, 60, 0, true, "Quad and hip flexor"));
            wes.add(new WorkoutExercise(12, 48, 6, 1, 0, 60, 0, false, "Final rest — breathe deeply"));
            db.workoutExerciseDao().insertAll(wes);
        }
    }
}