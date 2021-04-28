package com.rbinnovative.rentalotool.ui.activity;

import android.app.Application;
import com.rbinnovative.rentalotool.config.DaggerRepositoryToolIoC;
import com.rbinnovative.rentalotool.config.DaggerValidationServiceIoC;
import com.rbinnovative.rentalotool.config.RepositoryToolIoC;
import com.rbinnovative.rentalotool.config.ValidationServiceIoC;

public class MainApplication extends Application {

    RepositoryToolIoC appComponent = DaggerRepositoryToolIoC.create();
    ValidationServiceIoC validationIoC = DaggerValidationServiceIoC.create();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RepositoryToolIoC getAppComponent() {
        return appComponent;
    }
}
