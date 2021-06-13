package com.rbinnovative.rentalotool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.rbinnovative.rentalotool.R;
import com.rbinnovative.rentalotool.model.Order;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.RentaloToolClient;
import com.rbinnovative.rentalotool.ui.recicleview.OrdersRecyclerAdapter;
import com.rbinnovative.rentalotool.ui.recicleview.ToolsRecyclerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rbinnovative.rentalotool.utils.Constants.ACTIVITY_MAPPING_USER_ID;

public class OrdersActivity extends AppCompatActivity {

    @Inject
    RentaloToolClient rentaloToolClient;
    @BindView(R.id.imageView3)
    ImageView rentaloToolLogoImage;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private GoogleSignInClient googleSignInClient;
    private String currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_layout);
        ((MainApplication) getApplicationContext()).appComponent.inject(this);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(ACTIVITY_MAPPING_USER_ID)) {
            this.currentUserId = (String) getIntent().getExtras().get(ACTIVITY_MAPPING_USER_ID);
        }
        rentaloToolLogoImage.setOnClickListener((click) -> {
            Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LandingScrollingActivity.class);
            toolsLandingActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
            startActivity(toolsLandingActivityIntent);
        });
        rentaloToolClient.retrieveOrders(currentUserId,
                ((successRetrievedOrders) -> runOnUiThread(() -> prepareAndPopulateOrdersRecyclerView(successRetrievedOrders))),
                ((failureRetrieved) ->  runOnUiThread(() -> prepareAndPopulateOrdersRecyclerView(failureRetrieved))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout_menu_item) {
            googleSignInClient.signOut()
                    .addOnCompleteListener(this, task -> runOnUiThread(() -> {
                        if (task.isSuccessful()) {
                            Intent toolsLandingActivityIntent = new Intent(this.getApplicationContext(), LoginActivity.class);
                            startActivity(toolsLandingActivityIntent);
                        }
                    }));
        } else if (id == R.id.orders_menu_item) {
            Intent orderActivityIntent = new Intent(this.getApplicationContext(), OrdersActivity.class);
            orderActivityIntent.putExtra(ACTIVITY_MAPPING_USER_ID, currentUserId);
            startActivity(orderActivityIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareAndPopulateOrdersRecyclerView(Order[] orders) {
        OrdersRecyclerAdapter adapter = new OrdersRecyclerAdapter(orders, recyclerView.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
