package com.rbinnovative.rentalotool.ui.recicleview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rbinnovative.rentalotool.R;
import com.rbinnovative.rentalotool.config.DaggerRepositoryToolIoC;
import com.rbinnovative.rentalotool.controller.listener.OnSuccessListener;
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Tool;
import com.rbinnovative.rentalotool.service.web.RentaloToolClient;

import javax.inject.Inject;

public class HorizotalToolsRecyclerAdapter extends RecyclerView.Adapter<HorizotalToolsRecyclerAdapter.HorizontalViewHolder> {
    public static final String TAG = HorizotalToolsRecyclerAdapter.class.getSimpleName();

    @Inject
    RentaloToolClient rentaloToolClient;
    private final Category[] categories;
    private final Context context;
    private final OnSuccessListener<Tool[]> onSuccessCategoryProcessListener;

    public HorizotalToolsRecyclerAdapter(Category[] categories, Context context, OnSuccessListener<Tool[]> onSuccessListener ) {
        this.categories = categories;
        this.context = context;
        this.onSuccessCategoryProcessListener= onSuccessListener;
        DaggerRepositoryToolIoC.builder().build().inject(this);
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.horizontal_category_item, parent, false);
        return new HorizontalViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int positionSelected) {
        final Category tool = categories[positionSelected];
        holder.textView.setText(tool.getName());
        holder.relativeLayout.setOnClickListener(view -> loadOneToolActiviy(view, tool));
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    private void loadOneToolActiviy(View view, Category category) {
        rentaloToolClient.retrieveToolsByCategory(category.getId(), onSuccessCategoryProcessListener, onSuccessCategoryProcessListener);
        Log.d(TAG, "Pressed on tool:" + category);
    }

    public static class HorizontalViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ConstraintLayout relativeLayout;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textViewHorizontal);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutHorizontal);
        }
    }
}
