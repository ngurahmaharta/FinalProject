package com.ngurah.finalproject.model.trip;

import com.google.gson.annotations.SerializedName;

public class Trip {
    @SerializedName("id")
    public int id;
    @SerializedName("fare")
    public int fare;
    @SerializedName("journeyTime")
    public String journeyTime;
    @SerializedName("sourceStop")
    public Stop sourceStop;
    @SerializedName("destStop")
    public Stop destStop;
    @SerializedName("bus")
    public Bus bus;
    @SerializedName("agency")
    public Agency agency;

    public Trip() {
    }

    public Trip(int id, int fare, String journeyTime, Stop sourceStop, Stop destStop, Bus bus, Agency agency) {
        this.id = id;
        this.fare = fare;
        this.journeyTime = journeyTime;
        this.sourceStop = sourceStop;
        this.destStop = destStop;
        this.bus = bus;
        this.agency = agency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public String getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(String journeyTime) {
        this.journeyTime = journeyTime;
    }

    public Stop getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(Stop sourceStop) {
        this.sourceStop = sourceStop;
    }

    public Stop getDestStop() {
        return destStop;
    }

    public void setDestStop(Stop destStop) {
        this.destStop = destStop;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}

