package com.opalinskiy.ostap.retrofitexample;


import com.opalinskiy.ostap.retrofitexample.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Connect {
    @GET("ru/public/currency-cash.json")
    Call<DataResponse> connect();
}
