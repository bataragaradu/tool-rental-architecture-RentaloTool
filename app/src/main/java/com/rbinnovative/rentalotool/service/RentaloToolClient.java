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

    public void retrieveToolAvailability(Integer toolId, final OnSuccessListener<String[]> onSuccessListener,
                                 final OnErrorListener<String[]> onErrorListener) {
        new RetrieveToolAvailabilityTask(toolId, onSuccessListener, onErrorListener).execute();
    }

    public void retrieveToolsByCategory(Integer categoryId,OnSuccessListener<Tool[]> onSuccessCategoryProcessListener, OnSuccessListener<Tool[]> onSuccessCategoryProcessListener1) {
        new RetrieveToolsByCategoryTask(categoryId, onSuccessCategoryProcessListener, onSuccessCategoryProcessListener1).execute();
    }
}
