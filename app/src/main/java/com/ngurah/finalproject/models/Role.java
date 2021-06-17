package com.ngurah.finalproject.models;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("id")

    private int id;
    @SerializedName("role")

    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

