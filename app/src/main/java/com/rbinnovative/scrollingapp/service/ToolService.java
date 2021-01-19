package com.rbinnovative.scrollingapp.service;

import com.rbinnovative.scrollingapp.controller.listener.OnErrorListener;
import com.rbinnovative.scrollingapp.controller.listener.OnSuccessListener;
import com.rbinnovative.scrollingapp.model.Tool;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
    TODO: remove explanation https://medium.com/@jintin/retrofit-2-b0c80d5caadf
 */

public class ToolService {

    @Inject
    public ToolService() {
    }

    public void initDataSet(final OnSuccessListener<Tool[]> onSuccessListener,
                              final OnErrorListener<Tool[]> onErrorListener) {
        new RetrieveToolsTask(onSuccessListener, onErrorListener).execute();
    }
}
