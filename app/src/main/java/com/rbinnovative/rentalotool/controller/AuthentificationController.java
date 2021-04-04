package com.rbinnovative.rentalotool.controller;

import com.rbinnovative.rentalotool.controller.listener.OnErrorListener;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;

public interface AuthentificationController {

    /**
     *  It's used for creating an async task for login.
     *  The success is handled by the {@param onSuccessListener}  with the token as parameter, the same goes for
     *  onErrorListener.
     * @param username the user username
     * @param password the user password
     * @param onSuccessListener callback function for successful login with the token received as parameter
     * @param onErrorListener callback function with error message as parameter
     */
    public void login(String username, String password,
                      final OnSuccessListener<String> onSuccessListener,
                      final OnErrorListener onErrorListener);

    /**
     *  It's used for creating an async task for registration.
     *  The success is handled by the {@param onSuccessListener}, the same goes for
     *  onErrorListener.
     * @param username the user username
     * @param password the user password
     * @param onSuccessListener callback function for successful login with the token received as parameter
     * @param onErrorListener callback function with error message as parameter
     */
    public void register(String username, String password,
                         final OnSuccessListener<String> onSuccessListener,
                         final OnErrorListener onErrorListener);
}
