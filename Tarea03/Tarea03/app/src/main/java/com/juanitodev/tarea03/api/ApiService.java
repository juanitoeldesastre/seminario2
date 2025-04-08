package com.juanitodev.tarea03.api;

import com.juanitodev.tarea03.models.LoginRequest;
import com.juanitodev.tarea03.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest request);
}