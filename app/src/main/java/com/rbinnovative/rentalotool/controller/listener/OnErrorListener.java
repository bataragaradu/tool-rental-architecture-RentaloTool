package com.rbinnovative.rentalotool.controller.listener;

public interface OnErrorListener<T> {
    void onFailure(T failure);
}
