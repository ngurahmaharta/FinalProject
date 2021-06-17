package com.ngurah.finalproject.rest;

import com.ngurah.finalproject.models.ResponLogin;
import com.ngurah.finalproject.models.User;
import com.ngurah.finalproject.models.UserLogin;
import com.ngurah.finalproject.models.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAuthInterface {
    @POST("auth/login")
    Call<ResponLogin> login(@Body UserLogin userLogin);
    @POST("auth/register")
    Call<User> register(@Body UserRegister userRegister);
}
