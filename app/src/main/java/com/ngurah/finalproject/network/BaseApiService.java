package com.ngurah.finalproject.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import com.ngurah.finalproject.model.trip.Bus;
import com.ngurah.finalproject.model.trip.Ticket;
import com.ngurah.finalproject.model.trip.Trip;
import com.ngurah.finalproject.model.trip.TripSchedule;
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

    @GET("tripSchedule")
    Call<TripSchedule> getTripSchedule();

    @GET("trip")
    Call<List<Trip>> getTripList();

    //get all stop
    //get all ticket

    @GET("ticket")
    Call<List<Ticket>> getTicketList();



}