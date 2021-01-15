//package com.rbinnovative.scrollingapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.festitmove.client.controller.AuthentificationController;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class SignupActivity extends AppCompatActivity {
//
//    public static final String TAG = com.festitmove.client.activity.SignupActivity.class.getSimpleName();
//
//    @BindView(R.id.input_name)
//    EditText _nameText;
//    @BindView(R.id.input_email)
//    EditText _emailText;
//    @BindView(R.id.input_password)
//    EditText _passwordText;
//    @BindView(R.id.input_reEnterPassword)
//    EditText _reEnterPasswordText;
//    @BindView(R.id.btn_signup)
//    Button _signupButton;
//    @BindView(R.id.link_login)
//    TextView _loginLink;
//    private Snackbar snackbar;
//    private AuthentificationController loginController;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        ButterKnife.bind(this);
//        MainApplication mainApplication = (MainApplication) getApplication();
//        loginController = mainApplication.getControler();
//
//        _signupButton.setOnClickListener(v -> signup());
//        snackbar = Snackbar.make(findViewById(R.id.btn_signup), "Signing your account, please wait .. ", Snackbar
//                .LENGTH_INDEFINITE);
//        _loginLink.setOnClickListener(v -> {
//            // Finish the registration screen and return to the Login activity
//            Intent intent = new Intent(getApplicationContext(), com.festitmove.client.activity.LoginActivity.class);
//            startActivity(intent);
//            finish();
//            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        });
//    }
//
//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
//
//    private void signup() {
//        Log.d(TAG, "Signup");
//
//        if (!validate()) {
//            onRegisterFailed();
//            return;
//        }
//        _signupButton.setEnabled(false);
//        snackbar.show();
//
//        String name = _nameText.getText().toString();
//        String password = _passwordText.getText().toString();
//
//        loginController.register(name, password,
//                ((successMessage) -> runOnUiThread(this::onRegisterSuccess)),
//                ((failureMessage) -> runOnUiThread(this::onRegisterFailed)));
//    }
//
//    private void onRegisterFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
//        snackbar.dismiss();
//        _signupButton.setEnabled(true);
//        Log.i(TAG,"FAILURE REGISTRATION");
//    }
//
//    private void onRegisterSuccess() {
//        Log.i(TAG,"SUCCESS REGISTRATION");
//        _signupButton.setEnabled(true);
//        Intent intent = new Intent(getApplicationContext(), com.festitmove.client.activity.LoginActivity.class);
//        startActivity(intent);
//        snackbar.dismiss();
//        finish();
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//    }
//
//    private boolean validate() {
//        boolean valid = true;
//
//        String name = _nameText.getText().toString();
//        String email = _emailText.getText().toString();
//        String password = _passwordText.getText().toString();
//        String reEnterPassword = _reEnterPasswordText.getText().toString();
//
//        if (name.isEmpty() || name.length() < 3) {
//            _nameText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            _nameText.setError(null);
//        }
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _emailText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            _passwordText.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            _passwordText.setError(null);
//        }
//
//        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
//            _reEnterPasswordText.setError("Password Do not match");
//            valid = false;
//        } else {
//            _reEnterPasswordText.setError(null);
//        }
//        return valid;
//    }
//}
