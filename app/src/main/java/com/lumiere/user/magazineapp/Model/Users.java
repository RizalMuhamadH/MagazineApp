package com.lumiere.user.magazineapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 04/21/2017.
 */

public class Users {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
