package com.rbinnovative.rentalotool.ui.activity;

import android.app.Application;
import com.rbinnovative.rentalotool.config.DaggerRepositoryToolIoC;
import com.rbinnovative.rentalotool.config.RepositoryToolIoC;

public class MainApplication extends Application {

    RepositoryToolIoC appComponent = DaggerRepositoryToolIoC.create();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RepositoryToolIoC getAppComponent() {
        return appComponent;
    }
}
