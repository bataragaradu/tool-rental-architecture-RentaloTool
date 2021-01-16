package com.rbinnovative.scrollingapp.ui;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rbinnovative.scrollingapp.R;
import com.rbinnovative.scrollingapp.model.Tool;
import com.rbinnovative.scrollingapp.service.ToolService;
import com.rbinnovative.scrollingapp.ui.recicleview.ToolsRecyclerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

public class ScrollingActivity extends AppCompatActivity {

    @Inject
    ToolService toolService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext()).appComponent.inject(this);
        prepareUi();
//        prepareAndPopulateRecyclerView();
        toolService.initDataSet(
                ((successRetrievedTools) -> runOnUiThread(() -> this.prepareAndPopulateRecyclerView(successRetrievedTools))),
                ((failureRetrieved) -> runOnUiThread(this::onRegisterFailed)));
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

    private void prepareAndPopulateRecyclerView(Tool[] tools) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ToolsRecyclerAdapter adapter = new ToolsRecyclerAdapter(tools);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void onRegisterFailed() {
    }

    private void prepareUi() {
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        FloatingActionButton fab = findViewById(R.id.fab);
    }
}
