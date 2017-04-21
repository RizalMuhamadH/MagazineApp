package com.lumiere.user.magazineapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 04/21/2017.
 */

public class Respone {
    @SerializedName("respone")
//    @Expose
    private String respone;

    public String getRespone() {
        return respone;
    }
}
