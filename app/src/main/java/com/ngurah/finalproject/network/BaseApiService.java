package com.ngurah.finalproject.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.ngurah.finalproject.model.trip.Bus;
import com.ngurah.finalproject.model.trip.Stop;
import com.ngurah.finalproject.model.trip.Ticket;
import com.ngurah.finalproject.model.trip.TicketReservation;
import com.ngurah.finalproject.model.trip.Trip;
import com.ngurah.finalproject.model.trip.TripSchedule;
import com.ngurah.finalproject.model.user.ChangePasswordRequest;
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

    @GET("trip")
    Call<List<Trip>> getTripList();

    @GET("stop")
    Call<List<Stop>> getStopList();

    @GET("ticket")
    Call<List<Ticket>> getTicketList();
//
//    @GET("tripSchedule")
//    Call<List<TripSchedule>> getTripSchedules();
//
//    @GET("tripSchedule")
//    Call<List<TripSchedule>> getTripSchedulesParam(@Query("destStopId") Integer destStopId, @Query("from") String from, @Query("sourceStopId") Integer sourceStopId, @Query("to") String to);

    @GET("ticket")
    Call<List<Ticket>> getTickets(@Query("passengerId") String id);

    @POST("ticket")
    Call<Ticket> createTicket(@Body TicketReservation ticketReservation);

    @GET("tripSchedule")
    Call<List<TripSchedule>> getTripSchedules();

    @GET("tripSchedule")
    Call<List<TripSchedule>> getTripSchedulesParam(@Query("destStopId") Integer destStopId, @Query("from") String from, @Query("sourceStopId") Integer sourceStopId, @Query("to") String to);

    @GET("tripSchedule/{id}")
    Call<TripSchedule> getTripSchedule(@Path("id") String id);



}