package com.ngurah.finalproject.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import com.ngurah.finalproject.model.bus.Bus;
import com.ngurah.finalproject.model.user.ResponseUser;
import com.ngurah.finalproject.model.user.UserRegister;
import com.ngurah.finalproject.model.user.User;

import java.util.List;

public interface BaseApiService {

    @POST("auth/login")
    Call<ResponseUser> getLogin(@Body RequestBody params);

    @POST("auth/register")
    Call<User> register(@Body UserRegister userRegister);

    @GET("bus")
    Call<List<Bus>> getAllBus();



}