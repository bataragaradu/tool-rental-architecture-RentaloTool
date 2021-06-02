package com.rbinnovative.rentalotool.service.web.tasks;

import android.os.AsyncTask;

import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.api.ToolsApi;
import com.rbinnovative.rentalotool.service.web.listeners.OnErrorListener;
import com.rbinnovative.rentalotool.service.web.listeners.OnSuccessListener;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rbinnovative.rentalotool.utils.Constants.TOOLS_API__URL;

public class RetrieveToolsTask extends AsyncTask<Void, Void, Tool[]> {

    private final OnSuccessListener<Tool[]> onSuccessListener;
    private final OnErrorListener<Tool[]> onErrorListener;

    public RetrieveToolsTask(OnSuccessListener<Tool[]> onSuccessListener, OnErrorListener<Tool[]>
            onErrorListener) {
        this.onSuccessListener = onSuccessListener;
        this.onErrorListener = onErrorListener;
    }

    @Override
    protected Tool[] doInBackground(Void[] params) {

        Tool[] result = new Tool[0];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOOLS_API__URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ToolsApi service = retrofit.create(ToolsApi.class);

        try {
            Response<Tool[]> response =
                    service.getAllTools().execute();
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