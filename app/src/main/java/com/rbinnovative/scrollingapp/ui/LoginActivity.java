package com.rbinnovative.scrollingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rbinnovative.scrollingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private static final int REQUEST_SIGNUP = 0;
    private static final String USERNAME = "username";
//    private AuthentificationController authentificationController;
    private Snackbar snackbar;

    @BindView(R.id.input_email)
    EditText _emailText;

    @BindView(R.id.input_password)
    EditText _passwordText;

    @BindView(R.id.btn_login)
    Button _loginButton;

    @BindView(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        snackbar = Snackbar.make(findViewById(R.id.btn_login), "Login, please wait .. ", Snackbar.LENGTH_INDEFINITE);
        _loginButton.setOnClickListener(v -> loginClicked());
        _signupLink.setOnClickListener(v -> registerClicked());
    }

    /*
        Disable going back to the MainApplication
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void onLoginSuccess(String token) {
        _loginButton.setEnabled(false);
        snackbar.dismiss();
        Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
        intent.putExtra("token", token);
        intent.putExtra(USERNAME, _emailText.getText().toString());
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        snackbar.dismiss();
        _loginButton.setEnabled(true);
    }

    private void registerClicked() {
        // Start the Signup activity
        Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void loginClicked() {

        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);
        snackbar.show();
        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        authentificationController.login(username, password,
//                ((token) -> runOnUiThread(() -> onLoginSuccess(token))),
//                ((loginException) -> runOnUiThread(this::onLoginFailed)));
    }

    private boolean validate() {

        boolean valid = true;
        if ( _emailText.getText().toString().isEmpty()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if ( _passwordText.getText().toString().isEmpty()) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
