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

import static com.rbinnovative.scrollingapp.utils.Constants.TOOLS_API__URL;

class RetrieveToolsTask extends AsyncTask<Void, Void, Tool[]> {


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