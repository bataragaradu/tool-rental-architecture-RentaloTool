package com.rbinnovative.scrollingapp.ui;

import android.app.Application;

public class MainApplication extends Application {
//    ApplicationComponent appComponent = DaggerApplicationComponent.create();
//    private AuthentificationController controler;

    @Override
    public void onCreate() {
        super.onCreate();
//        DaggerMyApplicationComponent.create().inject(this);
//        ApplicationComponent appComponent = DaggerApplicationComponent.create();
//        controler = new AuthentificationControllerImpl(new OkHttpClient());
    }

//    public AuthentificationController getControler() {
//        return controler;
//    }
}
