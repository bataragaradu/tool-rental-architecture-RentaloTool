package com.rbinnovative.scrollingapp.ui.recicleview;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.rbinnovative.scrollingapp.R;
import com.rbinnovative.scrollingapp.model.Tool;
import com.rbinnovative.scrollingapp.ui.activity.DetailedToolActivity;
import com.squareup.picasso.Picasso;

public class ToolsRecyclerAdapter extends RecyclerView.Adapter<ToolsRecyclerAdapter.ViewHolder>{

    public static final String TAG = ToolsRecyclerAdapter.class.getSimpleName();

    private final Context context;
    private Tool[] listTools;

    public ToolsRecyclerAdapter(Tool[] listTools, Context context) {
        this.listTools = listTools;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int positionSelected) {
        final Tool tool = listTools[positionSelected];
        holder.textView.setText(tool.getName());
        Picasso.get()
                .load(tool.getImageUrl())
                .into(holder.imageView);
        holder.relativeLayout.setOnClickListener(view -> loadOneToolActiviy(view, tool));
    }

    private void loadOneToolActiviy(View view, Tool tool) {
        Log.d(TAG, "Pressed on tool:" + tool);
        Intent intent = new Intent(view.getContext(), DetailedToolActivity.class);
//        intent.getExtras().putInt("id", tool.getId().intValue());
//        intent.getExtras().putString("name", tool.getName());
//        intent.getExtras().putString("imageUrl", tool.getImageUrl());
        context.startActivity(intent);
        Toast.makeText(view.getContext(),"click on item: "+ tool.getId(),Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return listTools.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}