package com.ngurah.finalproject.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.ngurah.finalproject.model.user.ResponseUser;

public interface BaseApiService {

    @POST("auth/login")
    Call<ResponseUser> getLogin(@Body RequestBody params);



}