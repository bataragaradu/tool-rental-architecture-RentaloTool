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
import com.rbinnovative.rentalotool.model.Category;
import com.rbinnovative.rentalotool.model.Tool;

public class HorizotalToolsRecyclerAdapter extends RecyclerView.Adapter<HorizotalToolsRecyclerAdapter.HorizontalViewHolder> {
    public static final String TAG = HorizotalToolsRecyclerAdapter.class.getSimpleName();

    private final Category[] categories;
    private final Context context;

    public HorizotalToolsRecyclerAdapter(Category[] categories, Context context) {
        this.categories = categories;
        this.context = context;
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

    private void loadOneToolActiviy(View view, Category tool) {
        Log.d(TAG, "Pressed on tool:" + tool);
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
