package com.ngurah.finalproject.model.trip;

import com.google.gson.annotations.SerializedName;
import com.ngurah.finalproject.model.user.User;

public class Ticket {

    @SerializedName("id")
    private Integer id;

    @SerializedName("seatNumber")
    private Integer seatNumber;

    @SerializedName("cancellable")
    private Boolean cancellable;

    @SerializedName("journeyDate")
    private String journeyDate;

    @SerializedName("passenger")
    private User passenger;

    @SerializedName("tripSchedule")
    private TripSchedule tripSchedule;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getSeatNumber() { return seatNumber; }

    public void setSeatNumber(Integer seatNumber) { this.seatNumber = seatNumber; }

    public Boolean getCancellable() { return cancellable; }

    public void setCancellable(Boolean cancellable) { this.cancellable = cancellable; }

    public String getJourneyDate() { return journeyDate; }

    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public User getPassenger() { return passenger; }

    public void setPassenger(User passenger) { this.passenger = passenger; }

    public TripSchedule getTripSchedule() { return tripSchedule; }

    public void setTripSchedule(TripSchedule tripSchedule) { this.tripSchedule = tripSchedule; }
}
