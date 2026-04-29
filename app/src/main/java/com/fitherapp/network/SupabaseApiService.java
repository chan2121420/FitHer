package com.fitherapp.network;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;
import java.util.Map;

public interface SupabaseApiService {

    @POST("auth/v1/signup")
    Call<Map<String, Object>> signUp(@Body Map<String, String> credentials);

    @POST("auth/v1/token?grant_type=password")
    Call<Map<String, Object>> signIn(@Body Map<String, String> credentials);

    @POST("auth/v1/logout")
    Call<Void> signOut(@Header("Authorization") String token);

    @GET("rest/v1/workout_plans")
    Call<List<Map<String, Object>>> getWorkoutPlans(
            @Header("Authorization") String token,
            @Header("apikey") String apiKey);

    @POST("rest/v1/workout_history")
    Call<Map<String, Object>> saveWorkoutHistory(
            @Header("Authorization") String token,
            @Header("apikey") String apiKey,
            @Body Map<String, Object> history);

    @GET("rest/v1/workout_history")
    Call<List<Map<String, Object>>> getWorkoutHistory(
            @Header("Authorization") String token,
            @Header("apikey") String apiKey,
            @Query("user_id") String userId);
}