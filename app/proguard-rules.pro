-keep class com.fitherapp.models.** { *; }
-keep class com.fitherapp.database.** { *; }
-keepattributes *Annotation*
-keepclassmembers class * {
    @androidx.room.* <methods>;
}
-keep class com.github.mikephil.charting.** { *; }
-keep class com.bumptech.glide.** { *; }
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn retrofit2.**