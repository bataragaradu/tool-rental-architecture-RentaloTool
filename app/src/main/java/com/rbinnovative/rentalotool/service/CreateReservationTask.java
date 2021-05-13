package com.rbinnovative.rentalotool.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rbinnovative.rentalotool.controller.listener.OnErrorListener;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Order;
import com.rbinnovative.rentalotool.service.serializer.LocalDateAdapter;

import java.io.IOException;
import java.time.LocalDate;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rbinnovative.rentalotool.utils.Constants.ORDERS_API__URL;

public class CreateReservationTask extends AsyncTask<Void, Void, Void> {
    private final Order order;
    private final OnSuccessListener<Void> onSuccessReservationListener;
    private final OnErrorListener<Void> onFailureReservationListener;

    public CreateReservationTask(Order order, OnSuccessListener<Void> onSuccessReservationListener, OnErrorListener<Void> onFailureReservationListener) {
        this.order = order;
        this.onSuccessReservationListener = onSuccessReservationListener;
        this.onFailureReservationListener = onFailureReservationListener;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ORDERS_API__URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        OrdersApi service = retrofit.create(OrdersApi.class);

        try {
            Response<Integer> response =
                    service.createOrder(order).execute();
            if (response.isSuccessful()) {
                onSuccessReservationListener.onSuccess(null);
            } else {
                onFailureReservationListener.onFailure(null);
            }
        } catch (IOException e) {
            onFailureReservationListener.onFailure(null);
        }
        return null;
    }
}
