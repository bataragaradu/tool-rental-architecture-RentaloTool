package com.rbinnovative.rentalotool.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.rbinnovative.rentalotool.R;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.RentaloToolClient;
import com.rbinnovative.rentalotool.ui.recicleview.HorizotalToolsRecyclerAdapter;
import com.rbinnovative.rentalotool.ui.recicleview.ToolsRecyclerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rbinnovative.rentalotool.utils.Constants.ACTIVITY_MAPPING_CURRENT_TOOL;
import static com.rbinnovative.rentalotool.utils.Constants.ACTIVITY_MAPPING_USER_ID;

public class LandingScrollingActivity extends AppCompatActivity {

    @Inject
    RentaloToolClient rentaloToolClient;

    @BindView(R.id.imageView3)
    ImageView rentaloToolLogoImage;
//    @BindView(R.id.toolbar_layout)
//    CollapsingToolbarLayout toolBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.categoryVerticalRecyclerView)
    RecyclerView horizontalRecyclerView;
    private String currentUserId;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext()).appComponent.inject(this);
        setContentView(R.layout.landing_main_layout);
        ButterKnife.bind(this);
        prepareGoogleSignIn();
        if (getIntent().hasExtra(ACTIVITY_MAPPING_USER_ID)) {
            this.currentUserId = (String) getIntent().getExtras().get(ACTIVITY_MAPPING_USER_ID);
        }
        rentaloToolLogoImage.setOnClickListener((click) -> {
            Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LandingScrollingActivity.class);
            toolsLandingActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
            startActivity(toolsLandingActivityIntent);
        });
        rentaloToolClient.retrieveTools(
                ((successRetrievedTools) -> runOnUiThread(() -> prepareAndPopulateToolsReciclerView(successRetrievedTools))),
                ((failureRetrieved) ->  runOnUiThread(() -> prepareAndPopulateToolsReciclerView(failureRetrieved))));
        rentaloToolClient.retrieveCategory(
                ((successRetrievedCategory) -> runOnUiThread(() -> prepareAndPopulateCategoryRecyclerView(successRetrievedCategory))),
                ((failureRetrieved) ->  runOnUiThread(() -> prepareAndPopulateCategoryRecyclerView(failureRetrieved))));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            return true;
            googleSignInClient.signOut()
                    .addOnCompleteListener(this, task -> runOnUiThread(() -> {
                        if (task.isSuccessful()) {
                            Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LoginActivity.class);
                            startActivity(toolsLandingActivityIntent);
                        }
                    }));
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareAndPopulateToolsReciclerView(Tool[] tools) {
        ToolsRecyclerAdapter adapter = new ToolsRecyclerAdapter(tools, currentUserId, recyclerView.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void prepareAndPopulateCategoryRecyclerView(Category[] categories) {
        HorizotalToolsRecyclerAdapter adapter = new HorizotalToolsRecyclerAdapter(categories, horizontalRecyclerView.getContext(), (successRetrievedTools) -> runOnUiThread(() -> prepareAndPopulateToolsReciclerView(successRetrievedTools)));
        horizontalRecyclerView.setHasFixedSize(true);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        horizontalRecyclerView.setAdapter(adapter);
    }
}
