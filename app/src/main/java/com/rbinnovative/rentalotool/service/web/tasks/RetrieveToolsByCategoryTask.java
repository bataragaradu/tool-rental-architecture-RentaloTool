package com.rbinnovative.rentalotool.service.web.tasks;

import android.os.AsyncTask;

import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.api.ToolsApi;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rbinnovative.rentalotool.utils.Constants.TOOLS_API__URL;

public class RetrieveToolsByCategoryTask extends AsyncTask<Integer, Void, Tool[]> {
    private final Integer categoryId;
    private final OnSuccessListener<Tool[]> onSuccessListener;

    public RetrieveToolsByCategoryTask(Integer categoryId, OnSuccessListener<Tool[]> onSuccessCategoryProcessListener, OnSuccessListener<Tool[]> onSuccessCategoryProcessListener1) {
        this.categoryId = categoryId;
        this.onSuccessListener = onSuccessCategoryProcessListener;
    }


    @Override
    protected Tool[] doInBackground(Integer... integers) {

        Tool[] result = new Tool[0];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOOLS_API__URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ToolsApi service = retrofit.create(ToolsApi.class);

        try {
            Response<Tool[]> response =
                    service.getToolsByCategoryId(categoryId).execute();
            if (response.isSuccessful()) {
                // success
                result = response.body();
                onSuccessListener.onSuccess(result);
            } else {
                // fail
                onSuccessListener.onSuccess(result);
            }
        } catch (IOException e) {
            onSuccessListener.onSuccess(result);
        }
        return result;
    }
}
