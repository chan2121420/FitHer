# Add project specific ProGuard rules here.
-keep class com.fitherapp.models.** { *; }
-keep class com.fitherapp.database.** { *; }
-keepattributes *Annotation*
-keepclassmembers class * {
    @androidx.room.* <methods>;
}
# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }
