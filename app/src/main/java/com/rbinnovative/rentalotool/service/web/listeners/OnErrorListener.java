package com.rbinnovative.rentalotool.service.web.listeners;

public interface OnErrorListener<T> {
    void onFailure(T failure);
}
