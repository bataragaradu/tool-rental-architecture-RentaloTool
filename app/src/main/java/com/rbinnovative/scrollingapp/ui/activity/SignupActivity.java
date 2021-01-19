package com.rbinnovative.scrollingapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.rbinnovative.scrollingapp.R;
import com.rbinnovative.scrollingapp.service.ValidationService;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = SignupActivity.class.getSimpleName();
    @Inject
    ValidationService validationService;
    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;
    private Snackbar snackbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext()).validationIoC.inject(this);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        MainApplication mainApplication = (MainApplication) getApplication();
//        loginController = mainApplication.getControler();
        _signupButton.setOnClickListener(v -> signup());
        snackbar = Snackbar.make(findViewById(R.id.btn_signup), "Signing your account, please wait .. ", Snackbar
                .LENGTH_INDEFINITE);
        _loginLink.setOnClickListener(v -> {
            // Finish the registration screen and return to the Login activity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void signup() {
        Log.d(TAG, "Signup");
        if (!validationService.validateSignup(_nameText, _emailText, _passwordText, _reEnterPasswordText)) {
            onRegisterFailed();
            return;
        }
        _signupButton.setEnabled(false);
        snackbar.show();
        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();
        onRegisterSuccess();
//        loginController.register(name, password,
//                ((successMessage) -> runOnUiThread(this::onRegisterSuccess)),
//                ((failureMessage) -> runOnUiThread(this::onRegisterFailed)));
    }

    private void onRegisterFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        snackbar.dismiss();
        _signupButton.setEnabled(true);
        Log.i(TAG, "FAILURE REGISTRATION");
    }

    private void onRegisterSuccess() {
        Log.i(TAG, "SUCCESS REGISTRATION");
        _signupButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        snackbar.dismiss();
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
