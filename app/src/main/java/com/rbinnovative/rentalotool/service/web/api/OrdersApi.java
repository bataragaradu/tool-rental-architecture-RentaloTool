package com.rbinnovative.rentalotool.service.web.api;

import com.rbinnovative.rentalotool.model.Order;
import com.rbinnovative.rentalotool.model.Tool;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrdersApi {

    //Orders
    @POST("/orders")
    public Call<Integer> createOrder(@Body Order order);
    //Orders
    @GET("/orders")
    public  Call<Order[]> retrieveOrders(@Query("categoryId") String userId);
}
