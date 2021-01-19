package com.rbinnovative.scrollingapp.ui.activity;

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
import com.rbinnovative.scrollingapp.service.ValidationService;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private static final int REQUEST_SIGNUP = 0;
    private static final String USERNAME = "username";
    private Snackbar snackbar;

    @Inject
    ValidationService validationService;

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
        // dagger
        ((MainApplication) getApplicationContext()).validationIoC.inject(this);
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
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void loginClicked() {
        Log.d(TAG, "Login");
        if (!validationService.validateLoginAction(_emailText, _passwordText)) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);
        snackbar.show();
        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        onLoginSuccess("hardcoded-token");
//        authentificationController.login(username, password,
//                ((token) -> runOnUiThread(() -> onLoginSuccess(token))),
//                ((loginException) -> runOnUiThread(this::onLoginFailed)));
    }
}
