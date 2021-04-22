package com.rbinnovative.rentalotool.ui.activity;

import android.os.Bundle;

import com.rbinnovative.rentalotool.R;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.RentaloToolClient;
import com.rbinnovative.rentalotool.ui.recicleview.HorizotalToolsRecyclerAdapter;
import com.rbinnovative.rentalotool.ui.recicleview.ToolsRecyclerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingScrollingActivity extends AppCompatActivity {

    @Inject
    RentaloToolClient rentaloToolClient;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.toolbar_layout)
//    CollapsingToolbarLayout toolBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.categoryVerticalRecyclerView)
    RecyclerView horizontalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext()).appComponent.inject(this);
        setContentView(R.layout.landing_main_layout);
        ButterKnife.bind(this);
        prepareUi();
        rentaloToolClient.retrieveTools(
                ((successRetrievedTools) -> runOnUiThread(() -> prepareAndPopulateToolsReciclerView(successRetrievedTools))),
                ((failureRetrieved) ->  runOnUiThread(() -> prepareAndPopulateToolsReciclerView(failureRetrieved))));
        rentaloToolClient.retrieveCategory(
                ((successRetrievedCategory) -> runOnUiThread(() -> prepareAndPopulateCategoryRecyclerView(successRetrievedCategory))),
                ((failureRetrieved) ->  runOnUiThread(() -> prepareAndPopulateCategoryRecyclerView(failureRetrieved))));
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareAndPopulateToolsReciclerView(Tool[] tools) {
        ToolsRecyclerAdapter adapter = new ToolsRecyclerAdapter(tools, recyclerView.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void prepareAndPopulateCategoryRecyclerView(Category[] categories) {
        HorizotalToolsRecyclerAdapter adapter = new HorizotalToolsRecyclerAdapter(categories, horizontalRecyclerView.getContext());
        horizontalRecyclerView.setHasFixedSize(true);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        horizontalRecyclerView.setAdapter(adapter);
    }

    private void prepareUi() {
//        setSupportActionBar(toolbar);
//        toolBarLayout.setTitle(getTitle());
    }
}
