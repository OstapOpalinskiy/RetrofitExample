package com.opalinskiy.ostap.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opalinskiy.ostap.retrofitexample.model.DataResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DataResponse.class, new CustomDeserializer());
        final Gson gson = gsonBuilder.create();
//        final Gson gson = new Gson();

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://resources.finance.ua")
                .build();

        new Thread(new Runnable(){
            @Override
            public void run() {
                Connect connection = retrofit.create(Connect.class);
                Call<DataResponse> call = connection.connect();
                Response<DataResponse> response = null;
                try {
                    response = call.execute();
                    DataResponse data = response.body();
//                    Log.d("TAG", "List of currencies:" + data.getCurrencies().size());
//                    Log.d("TAG", "List of regions:" + data.getRegions().size());
//                    Log.d("TAG", "List of cities:" + data.getCities().size());
//                    Log.d("TAG", "List of date:" + data.getDate());
//                    Log.d("TAG", "List of orgTypes:" + data.getOrgTypes().size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
