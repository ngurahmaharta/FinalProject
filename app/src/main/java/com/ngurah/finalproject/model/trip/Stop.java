package com.ngurah.finalproject.model.trip;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stop {
    @SerializedName("id")
    public int id;
    @SerializedName("code")
    public String code;
    @SerializedName("name")
    public String name;
    @SerializedName("detail")
    public String detail;

    public Stop() {
    }

    public Stop(int id, String code, String name, String detail) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

