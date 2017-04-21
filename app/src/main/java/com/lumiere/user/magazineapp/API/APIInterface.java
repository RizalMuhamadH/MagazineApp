package com.lumiere.user.magazineapp.API;

import com.lumiere.user.magazineapp.Model.Respone;
import com.lumiere.user.magazineapp.Model.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by user on 04/21/2017.
 */

public interface APIInterface {
    @POST("login.php")
    Call<Respone> login(@Body Users users);

    @POST("registration.php")
    Call<Respone> registration(@Path("username") String username,@Path("email") String email, @Path("password") String password);
}
