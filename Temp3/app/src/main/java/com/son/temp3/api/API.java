package com.son.temp3.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.son.temp3.model.Ip;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface API {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    API apiService = new Retrofit.Builder()
            .baseUrl("http://ip-api.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(API.class);

    @GET("json")
    Call<Ip> getIpAdress();
}
