package com.ngurah.finalproject.models;

import com.google.gson.annotations.SerializedName;

public class ResponLogin {

    @SerializedName("user")

    private User user;
    @SerializedName("accessToken")

    private String accessToken;
    @SerializedName("tokenType")

    private String tokenType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

