package com.rbinnovative.rentalotool.service.web;

import com.rbinnovative.rentalotool.service.web.listeners.OnErrorListener;
import com.rbinnovative.rentalotool.service.web.listeners.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Order;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.tasks.CreateReservationTask;
import com.rbinnovative.rentalotool.service.web.tasks.RetrieveCategoryTask;
import com.rbinnovative.rentalotool.service.web.tasks.RetrieveToolAvailabilityTask;
import com.rbinnovative.rentalotool.service.web.tasks.RetrieveToolsByCategoryTask;
import com.rbinnovative.rentalotool.service.web.tasks.RetrieveToolsTask;

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

    public void retrieveToolsByCategory(Integer categoryId,OnSuccessListener<Tool[]> onSuccessCategoryProcessListener, OnSuccessListener<Tool[]> onErrorListener) {
        new RetrieveToolsByCategoryTask(categoryId, onSuccessCategoryProcessListener, onErrorListener).execute();
    }

    //TODO: Orders client(Or big client)
    public void makeReservation(Order order, OnSuccessListener<Void> onSuccessCategoryProcessListener, OnErrorListener<Void> onErrorListener) {
        new CreateReservationTask(order, onSuccessCategoryProcessListener, onErrorListener).execute();
    }
}
