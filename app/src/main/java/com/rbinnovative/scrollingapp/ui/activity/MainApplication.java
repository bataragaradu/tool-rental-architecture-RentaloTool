package com.rbinnovative.scrollingapp.ui.activity;

import android.app.Application;
import com.rbinnovative.scrollingapp.config.DaggerRepositoryToolIoC;
import com.rbinnovative.scrollingapp.config.DaggerValidationServiceIoC;
import com.rbinnovative.scrollingapp.config.RepositoryToolIoC;
import com.rbinnovative.scrollingapp.config.ValidationServiceIoC;

public class MainApplication extends Application {

    RepositoryToolIoC appComponent = DaggerRepositoryToolIoC.create();
    ValidationServiceIoC validationIoC = DaggerValidationServiceIoC.create();
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
