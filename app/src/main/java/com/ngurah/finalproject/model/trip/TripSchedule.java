package com.ngurah.finalproject.model.trip;

import com.google.gson.annotations.SerializedName;

public class TripSchedule {
    @SerializedName("id")
    public int id;
    @SerializedName("tripDate")
    public String tripDate;
    @SerializedName("availableSeats")
    public int availableSeats;
    @SerializedName("tripDetail")
    public Trip tripDetail;

    public TripSchedule() {
    }

    public TripSchedule(int id, String tripDate, int availableSeats, Trip tripDetail) {
        this.id = id;
        this.tripDate = tripDate;
        this.availableSeats = availableSeats;
        this.tripDetail = tripDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Trip getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(Trip tripDetail) {
        this.tripDetail = tripDetail;
    }
}
