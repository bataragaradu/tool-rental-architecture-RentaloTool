package com.rbinnovative.rentalotool.service;

import android.os.AsyncTask;

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

public class RetrieveToolAvailabilityTask extends AsyncTask<Integer, Void, LocalDate[]> {
    private final Integer toolId;
    private final OnErrorListener<LocalDate[]> onErrorListener;
    private final OnSuccessListener<LocalDate[]> onSuccessListener;

    public RetrieveToolAvailabilityTask(Integer toolId, OnSuccessListener<LocalDate[]> onSuccessListener, OnErrorListener<LocalDate[]> onErrorListener) {
        this.toolId = toolId;
        this.onSuccessListener = onSuccessListener;
        this.onErrorListener = onErrorListener;
    }

    @Override
    protected LocalDate[] doInBackground(Integer... voids) {
        LocalDate[] result = new LocalDate[0];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOOLS_API__URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ToolsApi service = retrofit.create(ToolsApi.class);

        try {
            Response<LocalDate[]> response =
                    service.getToolAvailability(toolId).execute();
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
