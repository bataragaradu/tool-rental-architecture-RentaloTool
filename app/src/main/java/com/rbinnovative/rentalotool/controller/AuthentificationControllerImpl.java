//package com.rbinnovative.scrollingapp.controller;
//
//import android.util.Log;
//
//import com.rbinnovative.scrollingapp.controller.listener.OnErrorListener;
//import com.rbinnovative.scrollingapp.controller.listener.OnSuccessListener;
//
//import java.util.concurrent.locks.ReentrantLock;
//
//
//public class AuthentificationControllerImpl implements AuthentificationController {
//
//    private static final String TAG = AuthentificationControllerImpl.class.getSimpleName();
//    private ReentrantLock loginLock = new ReentrantLock();
//
//    public AuthentificationControllerImpl() {
//    }
//
//    public void login(String username, String password,
//                      final OnSuccessListener<String> onSuccessListener,
//                      final OnErrorListener onErrorListener) {
//
//        Log.d(TAG, "Trying to login..");
//        if (loginLock.tryLock()) {
////            if (loginTask != null) {
////                loginTask.cancel(true);
////            }
//            onSuccessListener.onSuccess("HARDCODED-TOKEN");
////            loginTask = new LoginTask(username, password, onSuccessListener, onErrorListener, okHttpClient);
//            loginLock.unlock();
//        }
////        loginTask.execute();
//    }
//
//    @Override
//    public void register(String username, String password, OnSuccessListener<String> onSuccessListener, OnErrorListener onErrorListener) {
//        Log.d(TAG, "Trying to register..");
//        if (loginLock.tryLock()) {
//            if (registerTask != null) {
//                registerTask.cancel(true);
//            }
//            registerTask = new RegisterTask(username, password, onSuccessListener, onErrorListener, okHttpClient);
//            loginLock.unlock();
//        }
//        registerTask.execute();
//    }
//}
