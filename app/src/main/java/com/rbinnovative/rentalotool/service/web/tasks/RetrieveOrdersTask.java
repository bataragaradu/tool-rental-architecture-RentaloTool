package com.rbinnovative.rentalotool.service.web.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rbinnovative.rentalotool.model.Order;
import com.rbinnovative.rentalotool.service.web.api.OrdersApi;
import com.rbinnovative.rentalotool.service.web.listeners.OnErrorListener;
import com.rbinnovative.rentalotool.service.web.listeners.OnSuccessListener;
import com.rbinnovative.rentalotool.service.web.serializer.LocalDateAdapter;

import java.io.IOException;
import java.time.LocalDate;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rbinnovative.rentalotool.utils.Constants.ORDERS_API__URL;

public class RetrieveOrdersTask extends AsyncTask<Void, Void, Order[]> {
    private final String userId;
    private final OnSuccessListener<Order[]> onSuccessListener;
    private final OnErrorListener<Order[]> onErrorListener;

    public RetrieveOrdersTask(String userId, OnSuccessListener<Order[]> onSuccessListener, OnErrorListener<Order[]> onErrorListener) {
        this.userId = userId;
        this.onSuccessListener = onSuccessListener;
        this.onErrorListener = onErrorListener;
    }


    @Override
    protected Order[] doInBackground(Void... voids) {
        Order[] result = new Order[0];

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ORDERS_API__URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        OrdersApi service = retrofit.create(OrdersApi.class);

        try {
            Response<Order[]> response =
                    service.retrieveOrders(userId).execute();
            if (response.isSuccessful()) {
                // success
                result = response.body();
                onSuccessListener.onSuccess(result);
            } else {
                // fail
                onSuccessListener.onSuccess(result);
            }
        } catch (IOException e) {
            onErrorListener.onFailure(result);
        }
        return result;
    }
}
