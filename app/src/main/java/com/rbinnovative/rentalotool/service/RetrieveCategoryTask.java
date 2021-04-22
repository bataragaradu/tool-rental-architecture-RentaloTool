package com.rbinnovative.rentalotool.service;

import android.os.AsyncTask;

import com.rbinnovative.rentalotool.controller.listener.OnErrorListener;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Tool;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rbinnovative.rentalotool.utils.Constants.TOOLS_API__URL;

public class RetrieveCategoryTask extends AsyncTask<Void, Void, Category[]> {
    private final OnSuccessListener<Category[]> onSuccessListener;
    private final OnErrorListener<Category[]> onErrorListener;

    public RetrieveCategoryTask(OnSuccessListener<Category[]> onSuccessListener, OnErrorListener<Category[]> onErrorListener) {
        this.onSuccessListener = onSuccessListener;
        this.onErrorListener = onErrorListener;
    }


    @Override
    protected Category[] doInBackground(Void... voids) {
        Category[] result = new Category[0];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOOLS_API__URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ToolsApi service = retrofit.create(ToolsApi.class);

        try {
            Response<Category[]> response =
                    service.getAllCategories().execute();
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
