package com.rbinnovative.rentalotool.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rbinnovative.rentalotool.controller.listener.OnErrorListener;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Category;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rbinnovative.rentalotool.utils.Constants.TOOLS_API__URL;

public class RetrieveToolAvailabilityTask extends AsyncTask<Integer, Void, String[]> {
    private final Integer toolId;
    private final OnErrorListener<String[]> onErrorListener;
    private final OnSuccessListener<String[]> onSuccessListener;

    public RetrieveToolAvailabilityTask(Integer toolId, OnSuccessListener<String[]> onSuccessListener, OnErrorListener<String[]> onErrorListener) {
        this.toolId = toolId;
        this.onSuccessListener = onSuccessListener;
        this.onErrorListener = onErrorListener;
    }

    @Override
    protected String[] doInBackground(Integer... voids) {
        String[] result = new String[0];
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOOLS_API__URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ToolsApi service = retrofit.create(ToolsApi.class);
        //TODO: try response<String[]>
        try {
            Response<String[]> response =
                    service.getToolAvailability(toolId).execute();
            if (response.isSuccessful()) {
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
