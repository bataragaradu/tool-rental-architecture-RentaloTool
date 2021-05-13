package com.rbinnovative.rentalotool.service;

import com.rbinnovative.rentalotool.model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrdersApi {

    //Orders
    @POST("/orders")
    public Call<Integer> createOrder(@Body Order order);
}
