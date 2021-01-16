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
                              final OnErrorListener onErrorListener) {
        new RetrieveToolsTask(onSuccessListener, onErrorListener).execute();
    }

//    public Tool[] initDataSetOld() {
//        return new Tool[]{
//                new Tool("Email", android.R.drawable.ic_dialog_email),
//                new Tool("Info", android.R.drawable.ic_dialog_info),
//                new Tool("Delete", android.R.drawable.ic_delete),
//                new Tool("Dialer", android.R.drawable.ic_dialog_dialer),
//                new Tool("Alert", android.R.drawable.ic_dialog_alert),
//                new Tool("Map", android.R.drawable.ic_dialog_map),
//                new Tool("Email", android.R.drawable.ic_dialog_email),
//                new Tool("Info", android.R.drawable.ic_dialog_info),
//                new Tool("Delete", android.R.drawable.ic_delete),
//                new Tool("Dialer", android.R.drawable.ic_dialog_dialer),
//                new Tool("Alert", android.R.drawable.ic_dialog_alert),
//                new Tool("Map", android.R.drawable.ic_dialog_map),
//        };
//    }
}
