package com.rbinnovative.rentalotool.service;

import com.rbinnovative.rentalotool.controller.listener.OnErrorListener;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Tool;

import javax.inject.Inject;
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
