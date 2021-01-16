package com.rbinnovative.scrollingapp.service;

import com.rbinnovative.scrollingapp.model.Tool;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/*
    TODO Remove Explanation:
        // The @GET annotation tells retrofit that this request is a get type request.
    // The string value tells retrofit that the path of this request is
    // baseUrl + v1/cryptocurrency/listings/latest + query parameter.
//    @GET("v1/cryptocurrency/listings/latest")
    // Annotation @Query is used to define query parameter for request. Finally the request url will
    // look like that https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?convert=EUR.
//    fun getAllCryptocurrencies(@Query("convert") currency: String): Call<CryptocurrenciesLatest>
    // The return type for this function is Call with its type CryptocurrenciesLatest.
 */
public interface ToolsApi {
    @GET("/tools")
    public Call<Tool[]> getAllTools();
    @GET("/tools/{id}")
    public Call<Tool> getToolById(@Path("id") Integer id);
}
