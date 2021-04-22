package com.rbinnovative.rentalotool.service;

import com.rbinnovative.rentalotool.controller.listener.OnErrorListener;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Tool;

import java.time.LocalDate;

import javax.inject.Inject;
/*
    TODO: remove explanation https://medium.com/@jintin/retrofit-2-b0c80d5caadf
 */

public class RentaloToolClient  {

    @Inject
    public RentaloToolClient() {
    }

    public void retrieveTools(final OnSuccessListener<Tool[]> onSuccessListener,
                              final OnErrorListener<Tool[]> onErrorListener) {
        new RetrieveToolsTask(onSuccessListener, onErrorListener).execute();
    }


    public void retrieveCategory(final OnSuccessListener<Category[]> onSuccessListener,
                            final OnErrorListener<Category[]> onErrorListener) {
        new RetrieveCategoryTask(onSuccessListener, onErrorListener).execute();
    }

    public void retrieveToolAvailability(Integer toolId, final OnSuccessListener<LocalDate[]> onSuccessListener,
                                 final OnErrorListener<LocalDate[]> onErrorListener) {
        new RetrieveToolAvailabilityTask(toolId, onSuccessListener, onErrorListener).execute();
    }

}