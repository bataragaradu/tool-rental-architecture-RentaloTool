package com.rbinnovative.scrollingapp.ui;


import android.app.Application;

import com.rbinnovative.scrollingapp.config.DaggerRepositoryToolIoC;
import com.rbinnovative.scrollingapp.config.RepositoryToolIoC;


public class MainApplication extends Application {
//    private AuthentificationController controler;
    RepositoryToolIoC appComponent = DaggerRepositoryToolIoC.create();
    @Override
    public void onCreate() {
        super.onCreate();
//        controler = new AuthentificationControllerImpl(new OkHttpClient());
    }

}
