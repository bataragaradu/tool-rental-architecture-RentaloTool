package com.rbinnovative.scrollingapp.service;

import android.widget.EditText;

import javax.inject.Inject;

public class ValidationService {

    @Inject
    public ValidationService(){}

    public boolean validateLoginAction(EditText _emailText, EditText _passwordText) {

        boolean valid = true;
        if (_emailText.getText().toString().isEmpty()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (_passwordText.getText().toString().isEmpty()) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
