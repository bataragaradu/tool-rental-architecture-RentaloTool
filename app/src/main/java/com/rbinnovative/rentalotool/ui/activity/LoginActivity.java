package com.rbinnovative.rentalotool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.rbinnovative.rentalotool.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rbinnovative.rentalotool.utils.Constants.ACTIVITY_MAPPING_USER_ID;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private static final int REQUEST_SIGNUP = 0;
    private static final String USERNAME = "username";
    private static final int RC_SIGN_IN = 0;
    private Snackbar snackbar;

    //    @Inject
//    ValidationService validationService;
    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // dagger
//        ((MainApplication) getApplicationContext()).validationIoC.inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        snackbar = Snackbar.make(findViewById(R.id.sign_in_button), "Login, please wait .. ", Snackbar.LENGTH_INDEFINITE);
        prepareGoogleSignIn();
//        _signupLink.setOnClickListener(v -> registerClicked());
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this::signIn);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            onLoginSuccess(account.getId());
        }
    }

    private void signIn(View v) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            onLoginSuccess(account.toString());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    private void prepareGoogleSignIn() {
        // Configure Google Sign In
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    /*
        Disable going back to the MainApplication
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void onLoginSuccess(String token) {
        signInButton.setEnabled(false);
        snackbar.dismiss();
        Intent intent = new Intent(getApplicationContext(), LandingScrollingActivity.class);
        intent.putExtra(ACTIVITY_MAPPING_USER_ID, token);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        snackbar.dismiss();
        signInButton.setEnabled(true);
    }
}
