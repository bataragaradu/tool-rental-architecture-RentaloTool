package com.rbinnovative.rentalotool.config;

import com.rbinnovative.rentalotool.service.ValidationService;
import com.rbinnovative.rentalotool.ui.activity.LoginActivity;
import com.rbinnovative.rentalotool.ui.activity.SignupActivity;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component
public interface ValidationServiceIoC {
    ValidationService validationService();
    void inject(LoginActivity loginActivity);
    void inject(SignupActivity loginActivity);
}
