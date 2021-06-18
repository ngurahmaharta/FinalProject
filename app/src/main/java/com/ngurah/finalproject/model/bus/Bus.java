package com.ngurah.finalproject.model.bus;

import com.google.gson.annotations.SerializedName;
import com.ngurah.finalproject.model.agency.Agency;

public class Bus {
    @SerializedName("id")
    private Integer id;
    @SerializedName("code")
    private String code;
    @SerializedName("capacity")
    private Integer capacity;
    @SerializedName("make")
    private String make;
    @SerializedName("agency")
    private Agency agency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
