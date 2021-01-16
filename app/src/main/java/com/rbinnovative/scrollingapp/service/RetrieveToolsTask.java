package com.rbinnovative.scrollingapp.service;

import android.os.AsyncTask;

import com.rbinnovative.scrollingapp.controller.listener.OnErrorListener;
import com.rbinnovative.scrollingapp.controller.listener.OnSuccessListener;
import com.rbinnovative.scrollingapp.model.Tool;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrieveToolsTask extends AsyncTask<Void, Void, Tool[]> {

    private static final String TOOLS_API__URL = "http://192.168.0.195:8082/";

    private final OnSuccessListener<Tool[]> onSuccessListener;
    private final OnErrorListener onErrorListener;

    public RetrieveToolsTask(OnSuccessListener<Tool[]> onSuccessListener, OnErrorListener
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
            Response<Tool> oneTool =
                    service.getToolById(1).execute();
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
            System.out.println(e);
            onErrorListener.onFailure(e.getMessage());
            // fail
        }
        return result;
    }
}