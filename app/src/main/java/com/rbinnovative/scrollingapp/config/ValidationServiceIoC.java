package com.rbinnovative.scrollingapp.config;

import com.rbinnovative.scrollingapp.service.ValidationService;
import com.rbinnovative.scrollingapp.ui.activity.LoginActivity;
import com.rbinnovative.scrollingapp.ui.activity.SignupActivity;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component
public interface ValidationServiceIoC {
    ValidationService validationService();
    void inject(LoginActivity loginActivity);
    void inject(SignupActivity loginActivity);
}
